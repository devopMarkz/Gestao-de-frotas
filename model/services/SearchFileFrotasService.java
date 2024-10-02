package model.services;

import java.io.File;

public class SearchFileFrotasService implements SearchFileService {

	@Override
	public File getRegistroDeVeiculos() {
		return new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeVeiculos.txt");
	}

	@Override
	public File getRegistroDeMotoristas() {
		return new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeMotoristas.txt");
	}

	@Override
	public File getRegistroDeViagens() {
		return new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeViagens.txt");
	}

}
