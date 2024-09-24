package model.entities;

import java.time.LocalDate;

import model.entities.enums.CategoriaCNH;
import utils.DTFormatter;

public class Motorista {
	
	private String nome;
	private String cnh;
	private CategoriaCNH categoriaCNH;
	private LocalDate dataNascimento;
	private Boolean disponivel;
	
	// CONSTRUTORES
	
	public Motorista(String nome, String cnh, CategoriaCNH categoriaCNH, LocalDate dataNascimento, Boolean disponivel) {
		this.nome = nome;
		this.cnh = cnh;
		this.categoriaCNH = categoriaCNH;
		this.dataNascimento = dataNascimento;
		this.disponivel = disponivel;
	}

	// GETTERS E SETTERS

	public CategoriaCNH getCategoriaCNH() {
		return categoriaCNH;
	}


	public void setCategoriaCNH(CategoriaCNH categoriaCNH) {
		this.categoriaCNH = categoriaCNH;
	}


	public Boolean getDisponivel() {
		return disponivel;
	}


	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}


	public String getNome() {
		return nome;
	}


	public String getCnh() {
		return cnh;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	// MÉTODOS DA CLASSE
	
	public String toString() {
		return String.format("%s,%s,%s,%s,%s", this.nome, this.cnh, this.categoriaCNH.toString(), DTFormatter.fmt.format(dataNascimento), this.disponivel);
	}
	
}
