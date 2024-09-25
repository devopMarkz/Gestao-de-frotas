package application;

import java.util.Locale;

import model.entities.Frota;
import model.entities.Motorista;
import model.entities.Veiculo;
import model.entities.Viagem;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("****************** GESTÃO DE FROTA ******************\n");
		
		Locale.setDefault(Locale.US);
		
		Frota frota = new Frota();
		
		for (Veiculo veiculo : frota.getVeiculos()) {
			System.out.println(veiculo);
		}
		
		System.out.println();
		
		for (Motorista motorista : frota.getMotoristas()) {
			System.out.println(motorista);
		}
		
		System.out.println();
		
		for (Viagem viagem : frota.getViagens()) {
			System.out.println(viagem);
		}
		
		
		/* SUCESSO - ARQUIVO SALVANDO EM REGISTRODEVIAGENS.TXT
		 * Viagem viagem = new Viagem(1, new Onibus("ptj1d34", "Volvo", "Mercedes", 2022, 2000.00, false, 200.00, CategoriaVeiculo.ONIBUS, Combustivel.DIESEL, 32), new Motorista("Marcos", "1378322726", CategoriaCNH.C, LocalDate.parse("2992-09-25"), true), LocalDate.now());
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Frota.registroDeViagens, true))){
			bw.write(viagem.imprimirNoArquivo());
			bw.newLine();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}*/
		
		/* SUCESSO - ARQUIVO SALVANDO EM REGISTRODEVEICULOS.TXT
		 * Veiculo veiculo = new Onibus("ptj1d34", "Volvo", "Mercedes", 2022, 2000.00, false, 200.00, CategoriaVeiculo.ONIBUS, Combustivel.DIESEL, 32);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Frota.registroDeVeiculos))){
			bw.write(veiculo.imprimirNoArquivo());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}*/

		/* SUCESSO - ARQUIVO SALVANDO EM REGISTRODEFUNCIONARIOS.TXT
		 * Motorista motorista = new Motorista("Marcos", "1378322726", CategoriaCNH.C, LocalDate.parse("2992-09-25"), true);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Frota.registroDeMotoristas))){
			bw.write(motorista.imprimirNoArquivo());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}*/

	}

}
