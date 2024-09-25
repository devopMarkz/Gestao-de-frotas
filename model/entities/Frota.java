package model.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.enums.CategoriaCNH;
import model.entities.enums.CategoriaVeiculo;
import model.entities.enums.Combustivel;

public class Frota {
	
	public static File registroDeMotoristas = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeMotoristas.txt");
	public static File registroDeVeiculos = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeVeiculos.txt");
	public static File registroDeViagens = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeViagens.txt");

	
	private List<Veiculo> veiculos = new ArrayList<>();
	private List<Motorista> motoristas = new ArrayList<>();
	private List<Viagem> viagens = new ArrayList<>();
	
	// CONSTRUTOR
	
	public Frota() {
		
		try (Scanner readerMotorista = new Scanner(new BufferedReader(new FileReader(registroDeMotoristas)));
			 Scanner readerVeiculo = new Scanner(new BufferedReader(new FileReader(registroDeVeiculos)));
			 Scanner readerViagem = new Scanner(new BufferedReader(new FileReader(registroDeViagens)))) {
			
			try {
				while (readerMotorista.hasNextLine()) {
					String[] coluna = readerMotorista.nextLine().split(",");
					
					motoristas.add(new Motorista(coluna[0], coluna[1], CategoriaCNH.valueOf(coluna[2]), LocalDate.parse(coluna[3]), Boolean.parseBoolean(coluna[4])));
				}
			} catch (Exception e) {
				System.out.println("Error - Motorista: Motorista inválido.");
				e.printStackTrace();
			}
			
			try {
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
			
			try {
				while(readerViagem.hasNextLine()) {
					String[] coluna = readerViagem.nextLine().split(",");
					
					Veiculo veiculo = veiculos.stream().filter(x -> x.getPlaca().equalsIgnoreCase(coluna[1])).findFirst().orElse(null);
					
					Motorista motorista = motoristas.stream().filter(x -> x.getCnh().equalsIgnoreCase(coluna[2])).findFirst().orElse(null);
					
					viagens.add(new Viagem(Integer.valueOf(coluna[0]), veiculo, motorista, LocalDate.parse(coluna[3])));
				}
			} catch (Exception e) {
				System.out.println("Error - Viagem: " + e.getMessage());
				e.printStackTrace();
			}
			
			
		} catch (Exception e) {
			System.out.println("Error:  + Motorista inválido.");
		}
		
	}
	
	public void atualizarRegistroDeMotoristas() {
		String catchError = null;

		try (BufferedWriter writerMotorista = new BufferedWriter(new FileWriter(registroDeMotoristas, false))){
			for (Motorista motorista : motoristas) {
				catchError = motorista.imprimirNoArquivo();
				writerMotorista.write(motorista.imprimirNoArquivo());
			}
		} catch (Exception e) {
			System.out.println("Erro na impressão do motorista: " + catchError + " / " + e.getMessage());
		}
	}
	
	public void atualizarRegistroDeVeiculos() {
		String catchError = null;
		
		try (BufferedWriter writerVeiculo = new BufferedWriter(new FileWriter(registroDeVeiculos, false))){
			for (Veiculo veiculo : veiculos) {
				catchError = veiculo.imprimirNoArquivo();
				writerVeiculo.write(veiculo.imprimirNoArquivo());
			}
		} catch (Exception e) {
			System.out.println("Erro na impressão do veículo: " + catchError + " / " + e.getMessage());
		}
	}
	
	public void atualizarRegistroDeViagens() {
		String catchError = null;
		
		try (BufferedWriter writerViagem = new BufferedWriter(new FileWriter(registroDeViagens, false))){
			for (Viagem viagem: viagens) {
				catchError = viagem.imprimirNoArquivo();
				writerViagem.write(viagem.imprimirNoArquivo());
			}
		} catch (Exception e) {
			System.out.println("Erro na impressão de viagem." + catchError + " / " + e.getMessage());
		}
	}

	// GETTERS E SETTERS
	
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public List<Viagem> getViagens() {
		return viagens;
	}
	
	// MÉTODOS DA CLASSE
	
	public void adicionarVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
		atualizarRegistroDeVeiculos();
	}
	
	public void adicionarMotorista(Motorista motorista) {
		motoristas.add(motorista);
		atualizarRegistroDeMotoristas();
	}
	
	public void registrarViagem(Veiculo veiculo, Motorista motorista, LocalDate dataInicio, LocalDate dataFim) {
		viagens.add(new Viagem((viagens.size() + 1), veiculo, motorista, dataInicio));
		atualizarRegistroDeViagens();
	}

	public void listarVeiculosDisponiveis() {
		for (int i = 0; i < veiculos.size(); i++) {
			if(veiculos.get(i).getDisponivel()) System.out.println("Veículo " + i + " - " + veiculos.get(i).toString());
		}
	}
	
	public void listarMotoristasDisponiveis() {
		for (int i = 0; i < motoristas.size(); i++) {
			if(motoristas.get(i).getDisponivel()) System.out.println("Motorista " + i + " - " + motoristas.get(i).toString());
		}
	}
	
}
