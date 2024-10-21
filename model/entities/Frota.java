package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.enums.StatusViagem;
import model.exceptions.DataInvalidaException;
import model.exceptions.MotoristaJaCadastradoException;
import model.exceptions.ViagemCanceladaException;
import model.exceptions.ViagemConcluidaException;
import model.exceptions.ViagemInexistenteException;
import model.repositories.FrotaRepositoryService;
import utils.DTFormatter;

public class Frota {
	
	private FrotaRepositoryService frotaRepository;
	
	private List<Veiculo> veiculos = new ArrayList<>();
	private List<Motorista> motoristas = new ArrayList<>();
	private List<Viagem> viagens = new ArrayList<>();
	
	// CONSTRUTOR
	
	public Frota(FrotaRepositoryService frotaRepository) {
		this.frotaRepository = frotaRepository;
		this.veiculos = frotaRepository.carregarVeiculos();
		this.motoristas  = frotaRepository.carregarMotoristas();
		this.viagens = frotaRepository.carregarViagens();
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
		frotaRepository.atualizarRegistroDeVeiculos(this.veiculos);;
	}
	
	public void adicionarMotorista(Motorista motorista) throws MotoristaJaCadastradoException{
		verificaCadastro(motorista);
		motoristas.add(motorista);
		frotaRepository.atualizarRegistroDeMotoristas(this.motoristas);
	}
	
	private void verificaCadastro(Motorista motorista) throws MotoristaJaCadastradoException {
		if(motoristas.stream().filter(x -> x.getCnh().equals(motorista.getCnh())).findFirst().orElse(null) != null) {
			throw new MotoristaJaCadastradoException("A CNH " + motorista.getCnh() + " já se encontra cadastrada no sistema.");
		} 
	}
	
	public void registrarViagem(Veiculo veiculo, Motorista motorista, LocalDate dataInicio) throws DataInvalidaException{
		validarDataDeInicioDaViagem(dataInicio);
		viagens.add(new Viagem(viagens.size(), veiculo, motorista, dataInicio, StatusViagem.EM_ANDAMENTO));
		setarDisponibilidade(motorista, veiculo);
		frotaRepository.atualizarRegistroDeMotoristas(this.motoristas);
		frotaRepository.atualizarRegistroDeVeiculos(this.veiculos);
		frotaRepository.atualizarRegistroDeViagens(this.viagens);
	}
	
	public void finalizarViagem(int idViagem, LocalDate dataFim, Double kmPercorrido) throws ViagemInexistenteException, DataInvalidaException, ViagemCanceladaException, ViagemConcluidaException {
		validarFimDaViagem(idViagem, dataFim);
		viagens.get(idViagem).setDataFim(dataFim);
		viagens.get(idViagem).setKmPercorrido(kmPercorrido);
		viagens.get(idViagem).setStatusViagem(StatusViagem.CONCLUIDA);
		
		setarDisponibilidade(viagens.get(idViagem).getMotorista(), viagens.get(idViagem).getVeiculo());
		
		frotaRepository.atualizarRegistroDeVeiculos(this.veiculos);
		frotaRepository.atualizarRegistroDeMotoristas(this.motoristas);
		frotaRepository.atualizarRegistroDeViagens(this.viagens);
	}
	
	public void cancelarViagem(int idViagem, LocalDate dataFim, Double kmPercorrido) throws ViagemInexistenteException, DataInvalidaException, ViagemCanceladaException, ViagemConcluidaException{
		validarFimDaViagem(idViagem, dataFim);
		viagens.get(idViagem).setDataFim(dataFim);
		viagens.get(idViagem).setKmPercorrido(kmPercorrido);
		viagens.get(idViagem).setStatusViagem(StatusViagem.CANCELADA);
		
		setarDisponibilidade(viagens.get(idViagem).getMotorista(), viagens.get(idViagem).getVeiculo());
		
		frotaRepository.atualizarRegistroDeVeiculos(this.veiculos);
		frotaRepository.atualizarRegistroDeMotoristas(this.motoristas);
		frotaRepository.atualizarRegistroDeViagens(this.viagens);
	}
	
	private void validarDataDeInicioDaViagem(LocalDate dataInicio) throws DataInvalidaException{
		if(dataInicio.isBefore(LocalDate.now())) throw new DataInvalidaException("A data de início da viagem não pode ser retroativa. Data de início: " + dataInicio.format(DTFormatter.fmt) + " | Data de hoje: " + LocalDate.now().format(DTFormatter.fmt));
	}
	
	private void validarFimDaViagem(int idViagem, LocalDate dataFim) throws ViagemInexistenteException, DataInvalidaException, ViagemCanceladaException, ViagemConcluidaException{
		// Valida a data de fim da viagem e diz que ela não pode ser menor que a data de início
		if(dataFim.isBefore(viagens.get(idViagem).getDataInicio()) || dataFim.isBefore(LocalDate.now())) {
			throw new DataInvalidaException("A data do fim da viagem não pode vir antes da data de início. Data final: " + dataFim.format(DTFormatter.fmt) + " | Data de início: " + viagens.get(idViagem).getDataInicio().format(DTFormatter.fmt));
		}
		// Diz que a viagem não pode ser finalizada se já estiver concluída
		if(viagens.get(idViagem).getStatusViagem().name().equals(StatusViagem.CONCLUIDA.name())) {
			throw new ViagemConcluidaException("Não é possível finalizar a viagem " + idViagem + " porque ela já foi concluída.");
		}
		// Diz que a viagem não pode ser finalizada se já estiver cancelada
		if(viagens.get(idViagem).getStatusViagem().name().equals(StatusViagem.CANCELADA.name())) {
			throw new ViagemCanceladaException("Não é possível finalizar a viagem " + idViagem + " porque ela já foi cancelada.");
		}
		if(viagens.get(idViagem) == null) {
			throw new ViagemInexistenteException("A viagem de ID " + idViagem + " não existe.");
		}
	}
	
	// SETAR DISPONIBILIDADE DE VEÍCULO E MOTORISTA PARA TRUE OU FALSE AO INICIAR OU FINALIZAR UMA VIAGEM
	private void setarDisponibilidade(Motorista motorista, Veiculo veiculo) {
		if(motorista.getDisponivel() && veiculo.getDisponivel()) {
			motoristas.stream().filter(x -> x.equals(motorista)).findFirst().orElseThrow().setDisponivel(false);
			veiculos.stream().filter(x -> x.equals(veiculo)).findFirst().orElseThrow().setDisponivel(false);
		} else {
			motoristas.stream().filter(x -> x.equals(motorista)).findFirst().orElseThrow().setDisponivel(true);
			veiculos.stream().filter(x -> x.equals(veiculo)).findFirst().orElseThrow().setDisponivel(true);
		}
	}

	public void listarVeiculosDisponiveis() {
		
		veiculos.stream().filter(v -> v.getDisponivel()).forEach(System.out::println);
		
//		for (int i = 0; i < veiculos.size(); i++) {
//			if(veiculos.get(i).getDisponivel()) {
//				System.out.println("Veículo " + i + " - " + veiculos.get(i).toString());
//			}
//		}
	}
	
	public void listarMotoristasDisponiveis() {
		
		motoristas.stream().filter(m -> m.getDisponivel()).forEach(System.out::println);
		
//		for (int i = 0; i < motoristas.size(); i++) {
//			if(motoristas.get(i).getDisponivel()) {
//				System.out.println("Motorista " + i + " - " + motoristas.get(i).toString());
//			}
//		}
	}
	
	public void listarViagensEmAndamento() {
		
		viagens.stream().filter(v -> v.getStatusViagem().name().equals(StatusViagem.EM_ANDAMENTO.name())).forEach(System.out::println);
		
//		for (Viagem viagem : viagens) {
//			if(viagem.getStatusViagem().name().equals(StatusViagem.EM_ANDAMENTO.name())) {
//				System.out.println(viagem);
//			}
//		}
	}
	
	public void listarViagens() {
		
		viagens.stream().forEach(System.out::println);
		
//		for (Viagem viagem : viagens) {
//			System.out.println(viagem);
//		}
	}
	
	public void imprimirRelatorioDeViagem(int idViagem) throws ViagemInexistenteException {
		
		viagens.stream().filter(v -> v.getIdViagem() == idViagem && v.getIdViagem() != null).forEach(System.out::println);
		
//		if(viagens.stream().filter(x -> x.getIdViagem() == idViagem).findFirst().orElse(null) != null) {
//			Viagem viagem = viagens.stream().filter(x -> x.getIdViagem() == idViagem).findFirst().orElse(null);
//			
//			System.out.println(viagem.toString());
//		}
//		else {
//			throw new ViagemInexistenteException("A viagem de ID " + idViagem + " é inexistente.");
//		}
	}
	
	public Double calcularCustoTotalDaViagem(Integer idViagem) {
		return viagens.get(idViagem).calcularCustoViagem();
	}
	
}