package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;

import model.entities.Frota;
import model.entities.Motorista;
import model.entities.Onibus;
import model.entities.Viagem;
import model.entities.enums.CategoriaCNH;
import model.entities.enums.CategoriaVeiculo;
import model.entities.enums.Combustivel;

public class Program {

	public static void main(String[] args) {
		
		/* SUCESSO - ARQUIVO SALVANDO EM REGISTRODEVIAGENsS.TXT
		 * Viagem viagem = new Viagem(1, new Onibus("ptj1d34", "Volvo", "Mercedes", 2022, 2000.00, false, 200.00, CategoriaVeiculo.ONIBUS, Combustivel.DIESEL, 32), new Motorista("Marcos", "1378322726", CategoriaCNH.C, LocalDate.parse("2992-09-25"), true), LocalDate.now());
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Frota.registroDeViagens))){
			bw.write(viagem.toString());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}*/
		
		/* SUCESSO - ARQUIVO SALVANDO EM REGISTRODEVEICULOS.TXT
		 * Veiculo veiculo = new Onibus("ptj1d34", "Volvo", "Mercedes", 2022, 2000.00, false, 200.00, CategoriaVeiculo.ONIBUS, Combustivel.DIESEL, 32);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Frota.registroDeVeiculos))){
			bw.write(veiculo.toString());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}*/

		/* SUCESSO - ARQUIVO SALVANDO EM REGISTRODEFUNCIONARIOS.TXT
		 * Motorista motorista = new Motorista("Marcos", "1378322726", CategoriaCNH.C, LocalDate.parse("2992-09-25"), true);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(Frota.registroDeMotoristas))){
			bw.write(motorista.toString());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}*/

	}

}
