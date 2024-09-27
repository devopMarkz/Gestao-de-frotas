package application;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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
		} catch (DateTimeParseException e) {
			System.out.println("Data inválida. Tente novamente!");
		} catch(InputMismatchException e) {
			System.out.println("Dado inválido. Tente novamente!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Veículo/Motorista selecionado não existe. Tente novamente!");
		}
		catch (Exception e) {
			System.out.println(e.getMessage() + " // In Class Program.");
			e.printStackTrace();
		} 
		

	}

}