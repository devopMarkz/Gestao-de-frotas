package application;

import model.entities.Frota;

public record App(Frota frota) {
	
	public void startApplication(int escolha) {
		
		System.out.println("O QUE VOCÊ DESEJA? \n1: EFETUAR CADASTRO DE MOTORISTA\n2: INICIAR VIAGEM\n3: FINALIZAR VIAGEM\n4: CANCELAR VIAGEM");
		
		switch (escolha) {
		case 1: {
			
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

}