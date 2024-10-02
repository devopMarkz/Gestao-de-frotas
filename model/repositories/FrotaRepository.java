package model.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.Caminhao;
import model.entities.Motorista;
import model.entities.Onibus;
import model.entities.Veiculo;
import model.entities.Viagem;
import model.entities.enums.CategoriaCNH;
import model.entities.enums.CategoriaVeiculo;
import model.entities.enums.Combustivel;
import model.entities.enums.StatusViagem;
import model.services.SearchFileService;

public class FrotaRepository implements FrotaRepositoryService {
	
	private SearchFileService searchFileService;
	
	// CONSTRUTORES DA CLASSE
	
	public FrotaRepository() {
	}
	
	public FrotaRepository(SearchFileService searchFileService) {
		this.searchFileService = searchFileService;
	}
	
	// GETTERS E SETTERS
	
	public void setSearchFileService(SearchFileService searchFileService) {
		this.searchFileService = searchFileService;
	}
	
	// MÉTODOS DA CLASSE
	
	public List<Motorista> carregarMotoristas() {
		List<Motorista> motoristas = new ArrayList<>();
		
		try (Scanner readerMotorista = new Scanner(new BufferedReader(new FileReader(searchFileService.getRegistroDeMotoristas())))){
			while (readerMotorista.hasNextLine()) {
				String[] coluna = readerMotorista.nextLine().split(",");
				
				motoristas.add(new Motorista(coluna[0], coluna[1], CategoriaCNH.valueOf(coluna[2]), LocalDate.parse(coluna[3]), Boolean.parseBoolean(coluna[4])));
			}
		} catch (Exception e) {
			System.out.println("Error - Motorista: Motorista inválido.");
			e.printStackTrace();
		}
		
		return motoristas;
	}
	
	public List<Veiculo> carregarVeiculos() {
		List<Veiculo> veiculos = new ArrayList<>();
		
		try (Scanner readerVeiculo = new Scanner(new BufferedReader(new FileReader(searchFileService.getRegistroDeVeiculos())))){
			while(readerVeiculo.hasNextLine()) {
				Veiculo veiculo = null;
				
				String[] coluna = readerVeiculo.nextLine().split(",");
				
				if(coluna[7].toUpperCase().equalsIgnoreCase(CategoriaVeiculo.ONIBUS.toString())) {
					veiculo = new Onibus(coluna[0], coluna[1], coluna[2], Integer.parseInt(coluna[3]), Double.parseDouble(coluna[4]), Boolean.parseBoolean(coluna[5]), Double.parseDouble(coluna[6]), CategoriaVeiculo.valueOf(coluna[7]), Combustivel.valueOf(coluna[8]), Integer.valueOf(coluna[9]));
				} else if(coluna[7].toUpperCase().equalsIgnoreCase(CategoriaVeiculo.CAMINHAO.toString())){
					veiculo = new Caminhao(coluna[0], coluna[1], coluna[2], Integer.parseInt(coluna[3]), Double.parseDouble(coluna[4]), Boolean.parseBoolean(coluna[5]), Double.parseDouble(coluna[6]), CategoriaVeiculo.valueOf(coluna[7]), Combustivel.valueOf(coluna[8]), Double.parseDouble(coluna[9]), Integer.parseInt(coluna[10]));
				} 
				
				veiculos.add(veiculo);
			}
		} catch (Exception e) {
			System.out.println("Error - Veiculo: " + e.getMessage());
			e.printStackTrace();
		}
		
		return veiculos;
	}

	public List<Viagem> carregarViagens(){
		List<Viagem> viagens = new ArrayList<>();
		
		try (Scanner readerViagem = new Scanner(new BufferedReader(new FileReader(searchFileService.getRegistroDeViagens())))){
			while(readerViagem.hasNextLine()) {
				String[] coluna = readerViagem.nextLine().split(",");
				
				Veiculo veiculo = carregarVeiculos().stream().filter(x -> x.getPlaca().equalsIgnoreCase(coluna[1])).findFirst().orElse(null);
				
				Motorista motorista = carregarMotoristas().stream().filter(x -> x.getCnh().equalsIgnoreCase(coluna[2])).findFirst().orElse(null);
				
				if(StatusViagem.valueOf(coluna[6]).name().equals(StatusViagem.CONCLUIDA.name())) {
					viagens.add(new Viagem(Integer.parseInt(coluna[0]), veiculo, motorista, LocalDate.parse(coluna[3]), LocalDate.parse(coluna[4]), Double.parseDouble(coluna[5]), StatusViagem.valueOf(coluna[6])));
				} else if(StatusViagem.valueOf(coluna[6]).name().equals(StatusViagem.CANCELADA.name())) {
					viagens.add(new Viagem(Integer.valueOf(coluna[0]), veiculo, motorista, LocalDate.parse(coluna[3]), LocalDate.parse(coluna[4]), StatusViagem.CANCELADA));
				} else {
					viagens.add(new Viagem(Integer.valueOf(coluna[0]), veiculo, motorista, LocalDate.parse(coluna[3]), StatusViagem.EM_ANDAMENTO));
				}
				
			}
		} catch (Exception e) {
			System.out.println("Error - Viagem: " + e.getMessage());
			e.printStackTrace();
		}
		
		return viagens;
	}

	public void atualizarRegistroDeMotoristas(List<Motorista> motoristas) {
		String catchError = null;

		try (BufferedWriter writerMotorista = new BufferedWriter(new FileWriter(searchFileService.getRegistroDeMotoristas(), false))){
			for (Motorista motorista : motoristas) {
				catchError = motorista.imprimirNoArquivo();
				writerMotorista.write(motorista.imprimirNoArquivo());
				writerMotorista.newLine();
			}
		} catch (Exception e) {
			System.out.println("Erro na impressão do motorista: " + catchError + " / " + e.getMessage());
		}
	}

	public void atualizarRegistroDeVeiculos(List<Veiculo> veiculos) {
		String catchError = null;
		
		try (BufferedWriter writerVeiculo = new BufferedWriter(new FileWriter(searchFileService.getRegistroDeVeiculos(), false))){
			for (Veiculo veiculo : veiculos) {
				catchError = veiculo.imprimirNoArquivo();
				writerVeiculo.write(veiculo.imprimirNoArquivo());
				writerVeiculo.newLine();
			}
		} catch (Exception e) {
			System.out.println("Erro na impressão do veículo: " + catchError + " / " + e.getMessage());
		}
	}

	public void atualizarRegistroDeViagens(List<Viagem> viagens) {
		String catchError = null;
		
		try (BufferedWriter writerViagem = new BufferedWriter(new FileWriter(searchFileService.getRegistroDeViagens(), false))){
			for (Viagem viagem: viagens) {
				catchError = viagem.imprimirNoArquivo();
				writerViagem.write(viagem.imprimirNoArquivo());
				writerViagem.newLine();
			}
		} catch (Exception e) {
			System.out.println("Erro na impressão de viagem." + catchError + " / " + e.getMessage());
		}
	}
	
	
}