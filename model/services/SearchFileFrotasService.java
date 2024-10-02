package model.services;

import java.io.File;

public class SearchFileFrotasService implements SearchFileService {

	@Override
	public File getRegistroDeVeiculos() {
		return new File("C:\\Users\\Marcos Andre\\Desktop\\javaArqs\\Trabalhando com Arquivos\\RegistroDeVeiculos.txt");
	}

	@Override
	public File getRegistroDeMotoristas() {
		return new File("C:\\Users\\Marcos Andre\\Desktop\\javaArqs\\Trabalhando com Arquivos\\RegistroDeMotoristas.txt");
	}

	@Override
	public File getRegistroDeViagens() {
		return new File("C:\\Users\\Marcos Andre\\Desktop\\javaArqs\\Trabalhando com Arquivos\\RegistroDeViagens.txt");
	}

}
