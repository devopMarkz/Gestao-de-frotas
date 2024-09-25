package application;

import java.time.LocalDate;

import model.entities.Frota;
import model.entities.Motorista;
import model.entities.enums.CategoriaCNH;
import utils.ClassScanner;
import utils.DTFormatter;

public record App(Frota frota, int escolha) {
	
	public void startApplication() {
		
		switch (escolha) {
		case 1: {
			cadastrarMotorista();
			break;
		}
		case 2: {
			
			break;
		}
		case 3: {
			
			break;
		}
		case 4: {
			
			break;
		}
	}

	}
	
	private void cadastrarMotorista() {
		Integer catchError = 0;
		try {
			System.out.print("\n****************** CADASTRO DE MOTORISTA ******************\nNome: ");
			ClassScanner.sc.nextLine();
			catchError++;
			String nome = ClassScanner.sc.nextLine();
			
			System.out.print("Número da CNH: ");
			catchError++;
			String cnh = ClassScanner.sc.next();
			
			System.out.print("Categoria da CNH: ");
			catchError++;
			CategoriaCNH categoriaCNH = CategoriaCNH.valueOf(ClassScanner.sc.next().toUpperCase());
			
			System.out.print("Data de nascimento: ");
			catchError++;
			LocalDate dataNascimento = LocalDate.parse(ClassScanner.sc.next(), DTFormatter.fmt);
			
			frota.adicionarMotorista(new Motorista(nome, cnh, categoriaCNH, dataNascimento, true));
		} catch (Exception e) {
			System.out.println("Erro no requisito " + catchError + " da class App (Record) - Método cadastrarMotorista() / " + e.getMessage() + "\nA informação passada está incorreta. Tente novamente!");
		}
	}

}