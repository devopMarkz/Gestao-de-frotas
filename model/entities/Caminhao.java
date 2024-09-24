package model.entities;

import model.entities.enums.CategoriaVeiculo;
import model.entities.enums.Combustivel;

public class Caminhao extends Veiculo {
	
	private Double capacidadeCarga;
	private Integer numeroDeEixos;
	
	// CONSTRUTORES
	
	public Caminhao() {
		super();
	}
	
	public Caminhao(String placa, String marca, String modelo, Integer anoFabricacao, Double capacidade, Boolean disponivel, Double custoPorDia, CategoriaVeiculo categoria, Combustivel combustivel, Double capacidadeCarga, Integer numeroDeEixos) {
		super(placa, marca, modelo, anoFabricacao, capacidade, disponivel, custoPorDia, categoria, combustivel);
		this.capacidadeCarga = capacidadeCarga;
		this.numeroDeEixos = numeroDeEixos;
	}
	
	// GETTERS E SETTERS

	public Double getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public Integer getNumeroDeEixos() {
		return numeroDeEixos;
	}
	
	@Override
	public String toString() {
		return String.format("%s,%.2f,%d", super.toString(), this.capacidadeCarga, this.numeroDeEixos);
	}
	
}
