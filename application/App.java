package application;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.InputMismatchException;
=======
>>>>>>> a1062c7afe82350f59337ee4485f4ab0a4d42a29

import model.entities.Frota;
import model.entities.Motorista;
import model.entities.Veiculo;
import model.entities.enums.CategoriaCNH;
import model.entities.enums.StatusViagem;
import model.exceptions.DataInvalidaException;
import model.exceptions.MotoristaIndisponivelException;
import model.exceptions.OpcaoInvalidaException;
import model.exceptions.VeiculoIndisponivelException;
import model.exceptions.ViagemCanceladaException;
import model.exceptions.ViagemConcluidaException;
import model.exceptions.ViagemInexistenteException;
import utils.ClassScanner;
import utils.DTFormatter;

public record App(Frota frota, int escolha) {
	
	public void startApplication() throws OpcaoInvalidaException{
		
		switch (escolha) {
		case 1: {
			cadastrarMotorista();
			break;
		}
		case 2: {
			verificarVeiculosDisponiveis();
			break;
		}
		case 3: {
			verificarMotoristasDisponiveis();
			break;
		}
		case 4: {
			iniciarViagem();
			break;
		}
		case 5: {
			finalizarViagem();
			break;
		}
		case 6: {
			cancelarViagem();
			break;
		}
		case 7: {
			imprimirRelatorioDeViagem();
			break;
		}
		default: {
			throw new OpcaoInvalidaException("A opção " + this.escolha + " é inexistente.");
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
		} catch (IllegalArgumentException e) {
			switch (catchError) {
			case 1: {
				System.out.println("O nome de usuário inserido não é válido.");
				break;
			}
			case 2: {
				System.out.println("O número da CNH inserido não é válido.");
				break;
			}
			case 3: {
				System.out.println("A categoria da CNH é inexistente.");
				break;
			}
			case 4: {
				System.out.println("A data de nascimento é inválida. Formato correto: dd/MM/yyyy.");
			}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void verificarVeiculosDisponiveis() {
		System.out.println("\n****************** LISTAGEM DE VEÍCULOS DISPONÍVEIS ******************\n");
		frota.listarVeiculosDisponiveis();
	}
	
	private void verificarMotoristasDisponiveis() {
		System.out.println("\n****************** LISTAGEM DE MOTORISTAS DISPONÍVEIS ******************\n");
		frota.listarMotoristasDisponiveis();
	}

	private void iniciarViagem() {
		
		try {
			verificarMotoristasDisponiveis();
			verificarVeiculosDisponiveis();
			
			System.out.print("\n****************** INICIE SUA VIAGEM ******************\n\nNº Veículo: ");
			int numeroDoVeiculo = ClassScanner.sc.nextInt();
			Veiculo veiculo = (frota.getVeiculos().get(numeroDoVeiculo).getDisponivel())? frota.getVeiculos().get(numeroDoVeiculo) : null;
			
			System.out.print("Nº Motorista: ");
			int numeroDoMotorista = ClassScanner.sc.nextInt();
			Motorista motorista = (frota.getMotoristas().get(numeroDoMotorista).getDisponivel())? frota.getMotoristas().get(numeroDoMotorista) :null;
			
			if(veiculo != null && veiculo.getDisponivel()) {
				if(motorista != null && motorista.getDisponivel()) {
					System.out.print("Data de início da viagem (dd/MM/yyyy): ");
					LocalDate dataInicio = LocalDate.parse(ClassScanner.sc.next(), DTFormatter.fmt);
					
					frota.registrarViagem(veiculo, motorista, dataInicio);
					System.out.println("VIAGEM INICIADA!");
				} else {
					throw new MotoristaIndisponivelException("O motorista " + numeroDoMotorista + " está indisponível.");
				}
			} else {
				throw new VeiculoIndisponivelException("O veículo " + numeroDoVeiculo + " está indisponível.");
			}
		} catch (MotoristaIndisponivelException e) {
			System.out.println(e.getMessage());
		} catch (VeiculoIndisponivelException e) {
			System.out.println(e.getMessage());
		} catch (DataInvalidaException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("Tipo de dado inválido. Tente novamente!");
		}
		
	}

	private void finalizarViagem() {
		try {
			System.out.print("\n****************** FINALIZE SUA VIAGEM ******************\n\n");
			
			frota.listarViagensEmAndamento();
			
			System.out.print("\nID da viagem que deseja finalizar: ");
			int idDaViagem = ClassScanner.sc.nextInt();
			
			System.out.print("Data do fim da viagem (dd/MM/yyyy): ");
			LocalDate dataFim = LocalDate.parse(ClassScanner.sc.next(), DTFormatter.fmt);
			
			System.out.print("Kilômetros percorridos: ");
			Double kmPercorridos = ClassScanner.sc.nextDouble();
			
			frota.finalizarViagem(idDaViagem, dataFim, kmPercorridos);
		} catch (ViagemInexistenteException e) {
			System.out.println(e.getMessage());
		} catch (DataInvalidaException e) {
			System.out.println(e.getMessage());
		} catch (ViagemCanceladaException e) {
			System.out.println(e.getMessage());
		} catch (ViagemConcluidaException e) {
			System.out.println(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("A viagem selecionada não existe nos nossos registros.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void cancelarViagem() {
		try {
			System.out.print("\n****************** CANCELE SUA VIAGEM ******************\n\n");
			
			frota.listarViagensEmAndamento();
			
			System.out.print("\nID da viagem que deseja cancelar: ");
			int idDaViagem = ClassScanner.sc.nextInt();
			
			frota.cancelarViagem(idDaViagem, frota.getViagens().get(idDaViagem).getDataInicio(), 0.0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Essa viagem é inexistente.");
		} catch (DataInvalidaException e) {
			System.out.println(e.getMessage());
		} catch (ViagemCanceladaException e) {
			System.out.println(e.getMessage());
		} catch (ViagemInexistenteException e) {
			System.out.println(e.getMessage());
		} catch (ViagemConcluidaException e) {
			System.out.println(e.getMessage());
		}
	}	
	
	private void imprimirRelatorioDeViagem() {
		try {
			System.out.print("\n****************** IMPRIMA O RELATÓRIO DE UMA VIAGEM ******************\n\nLISTA DE VIAGENS:\n");
			
			frota.listarViagens();
			
			System.out.print("\nID da viagem que deseja obter relatório: ");
			int idDaViagem = ClassScanner.sc.nextInt();
			
			frota.imprimirRelatorioDeViagem(idDaViagem);
			if(frota.getViagens().get(idDaViagem).getStatusViagem().name().equals(StatusViagem.CONCLUIDA.name()) || frota.getViagens().get(idDaViagem).getStatusViagem().name().equals(StatusViagem.CANCELADA.name())) {
				System.out.print("Custo da Viagem: R$ " + String.format("%.2f", frota.calcularCustoTotalDaViagem(idDaViagem)));
				
			}
		} catch (ViagemInexistenteException e) {
			System.out.println(e.getMessage());
		}
	}
}