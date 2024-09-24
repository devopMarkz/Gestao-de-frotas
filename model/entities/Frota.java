package model.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frota {
	
	public static File registroDeMotoristas = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeMotoristas.txt");
	public static File registroDeVeiculos = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeVeiculos.txt");
	public static File registroDeViagens = new File("C:\\Users\\marcos.andre\\Desktop\\Suprimentos CPL\\arquivos java\\Atividade_GPT_All+Files\\gestao-de-frota\\RegistroDeViagens.txt");

	
	private List<Veiculo> veiculos = new ArrayList<>();
	private List<Motorista> motoristas = new ArrayList<>();
	private List<Viagem> viagens = new ArrayList<>();
	
	// CONSTRUTOR
	
	public Frota() {
		

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
