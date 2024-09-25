package model.entities;

import model.entities.enums.CategoriaVeiculo;
import model.entities.enums.Combustivel;

public abstract class Veiculo {
	
	private String placa;
	private String marca;
	private String modelo;
	private Integer anoFabricacao;
	private Double capacidade;
	private Boolean disponivel;
	private Double custoPorDia;
	private CategoriaVeiculo categoria;
	private Combustivel combustivel;
	
	// CONSTRUTORES
	
	public Veiculo() {
	}
	
	public Veiculo(String placa, String marca, String modelo, Integer anoFabricacao, Double capacidade, Boolean disponivel, Double custoPorDia, CategoriaVeiculo categoria, Combustivel combustivel) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.capacidade = capacidade;
		this.disponivel = disponivel;
		this.custoPorDia = custoPorDia;
		this.categoria = categoria;
		this.combustivel = combustivel;
	}
	
	// GETTERS E SETTERS

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Double getCustoPorDia() {
		return custoPorDia;
	}

	public void setCustoPorDia(Double custoPorDia) {
		this.custoPorDia = custoPorDia;
	}

	public String getPlaca() {
		return placa;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public Double getCapacidade() {
		return capacidade;
	}

	public CategoriaVeiculo getCategoria() {
		return categoria;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}
	
	// MÉTDOS DA CLASSE
	
	public String imprimirNoArquivo() {
		return String.format("%s,%s,%s,%d,%.2f,%s,%.2f,%s,%s", this.placa, this.marca, this.modelo, this.anoFabricacao, this.capacidade, this.disponivel, this.custoPorDia, this.categoria, this.combustivel);
	}
	
	@Override
	public String toString() {
		return String.format("Disponibilidade: %s | Placa: %s | Marca: %s | Modelo: %s | Fabricação: %d | Capacidade: %.2fkg | Custo P/Dia: R$ %.2f | Categoria: %s | Combustível: %s", this.disponivel, this.placa, this.marca, this.modelo, this.anoFabricacao, this.capacidade, this.custoPorDia, this.categoria, this.combustivel);
	}

}
