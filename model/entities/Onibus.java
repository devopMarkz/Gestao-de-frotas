package model.entities;

import model.entities.enums.CategoriaVeiculo;
import model.entities.enums.Combustivel;

public class Onibus extends Veiculo {
	
	private Integer numeroDeAssentos;
	
	// CONSTRUTORES
	
	public Onibus() {
		super();
	}

	public Onibus(String placa, String marca, String modelo, Integer anoFabricacao, Double capacidade, Boolean disponivel, Double custoPorDia, CategoriaVeiculo categoria, Combustivel combustivel, Integer numeroDeAssentos) {
		super(placa, marca, modelo, anoFabricacao, capacidade, disponivel, custoPorDia, categoria, combustivel);
		this.numeroDeAssentos = numeroDeAssentos;
	}
	
	// GETTERS E SETTERS

	public Integer getNumeroDeAssentos() {
		return numeroDeAssentos;
	}
	
	@Override
	public String imprimirNoArquivo() {
		return String.format("%s,%d", super.imprimirNoArquivo(), this.numeroDeAssentos);
	}
	
	@Override
	public String toString() {
		return String.format("%s | %d assentos", super.toString(), this.numeroDeAssentos);
	}
	
	@Override
	public int compareTo(Veiculo o) {
		return - super.getCategoria().compareTo(o.getCategoria());
	}

}
