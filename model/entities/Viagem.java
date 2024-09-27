package model.entities;

import java.time.LocalDate;
import java.time.Period;

import model.entities.enums.StatusViagem;

public class Viagem {

	private Integer idViagem;
	private Veiculo veiculo;
	private Motorista motorista;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Double kmPercorrido;
	private StatusViagem statusViagem;
	
	// CONSTRUTORES
	
	// SOBRECARGA PARA INÍCIO DE VIAGEM
	public Viagem(Integer idViagem, Veiculo veiculo, Motorista motorista, LocalDate dataInicio, StatusViagem status) {
		this.idViagem = idViagem;
		this.veiculo = veiculo;
		this.motorista = motorista;
		this.dataInicio = dataInicio;
		this.dataFim = null;
		this.kmPercorrido = 0.0;
		this.statusViagem = status;
	}
	
	// SOBRECARGA PARA VIAGEM CONCLUÍDA
	public Viagem(Integer idViagem, Veiculo veiculo, Motorista motorista, LocalDate dataInicio, LocalDate dataFim, Double kmPercorrido, StatusViagem statusViagem) {
		this.idViagem = idViagem;
		this.veiculo = veiculo;
		this.motorista = motorista;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.kmPercorrido = kmPercorrido;
		this.statusViagem = statusViagem;
	}
	
	// SOBRECARGA PARA VIAGEM CANCELADA
	public Viagem(Integer idViagem, Veiculo veiculo, Motorista motorista, LocalDate dataInicio, LocalDate dataFim, StatusViagem statusViagem) {
		this.idViagem = idViagem;
		this.veiculo = veiculo;
		this.motorista = motorista;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.kmPercorrido = 0.0;
		this.statusViagem = statusViagem;
	}



	// GETTERS E SETTERS

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Double getKmPercorrido() {
		return kmPercorrido;
	}

	public void setKmPercorrido(Double kmPercorrido) {
		this.kmPercorrido = kmPercorrido;
	}

	public Integer getIdViagem() {
		return idViagem;
	}
	
	public StatusViagem getStatusViagem() {
		return statusViagem;
	}

	public void setStatusViagem(StatusViagem statusViagem) {
		this.statusViagem = statusViagem;
	}
	
	// MÉTODOS DA CLASSE
	
	public Integer calcularDuracao() {
		return Period.between(dataInicio, dataFim).getDays();
	}

	public Double calcularCustoViagem() {
		return this.calcularDuracao() * veiculo.getCustoPorDia();
	}
	
	public String imprimirNoArquivo() {
		return String.format("%d,%s,%s,%s,%s,%.2f,%s", this.idViagem, this.veiculo.getPlaca(), this.motorista.getCnh(), this.dataInicio, this.dataFim, this.kmPercorrido, this.statusViagem.toString());
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d | Veículo/Placa: %s | Motorista (CNH): %s | Início da viagem: %s | Fim da viagem: %s | KM percorrido: %.1f | Status da viagem: %s", this.idViagem, this.veiculo.getPlaca(), this.motorista.getCnh(), this.dataInicio, this.dataFim, this.kmPercorrido, this.statusViagem.toString());
	}
	
}
