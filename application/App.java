package application;

import java.time.LocalDate;

import model.entities.Frota;
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
		System.out.print("\n****************** CADASTRO DE MOTORISTA ******************\nNome: ");
		ClassScanner.sc.nextLine();
		String nome = ClassScanner.sc.nextLine();
		
		System.out.print("Número da CNH: ");
		String cnh = ClassScanner.sc.next();
		
		System.out.print("Categoria da CNH: ");
		CategoriaCNH categoriaCNH = CategoriaCNH.valueOf(ClassScanner.sc.next().toUpperCase());
		
		System.out.print("Data de nascimento: ");
		LocalDate dataNascimento = LocalDate.parse(ClassScanner.sc.next(), DTFormatter.fmt);
		
		frota.listarMotoristasDisponiveis();
	}

}