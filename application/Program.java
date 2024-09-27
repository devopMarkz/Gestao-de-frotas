package application;

import java.util.Locale;

import model.entities.Frota;
import utils.ClassScanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		try {
			Frota frota = new Frota(); 
			
			System.out.println("****************** GESTÃO DE FROTA ******************\n");
			System.out.print("O QUE VOCÊ DESEJA? \n1: EFETUAR CADASTRO DE MOTORISTA\n2: VERIFICAR VEÍCULOS DISPONÍVEIS\n3: VERIFICAR MOTORISTAS DISPONÍVEIS\n4: INICIAR VIAGEM\n5: FINALIZAR VIAGEM\n6: CANCELAR VIAGEM\n7: RELATÓRIO DE VIAGEM\nRESPOSTA: ");
			
			int escolha = ClassScanner.sc.nextInt();
			
			App app = new App(frota, escolha);
			
			app.startApplication();		
		} catch (Exception e) {
			System.out.println(e.getMessage() + " // In Class Program.");
		}
		

	}

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
