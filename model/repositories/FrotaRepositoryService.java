package model.repositories;

import java.util.List;

import model.entities.Motorista;
import model.entities.Veiculo;
import model.entities.Viagem;

public interface FrotaRepositoryService {
	
	public List<Motorista> carregarMotoristas();
	public List<Veiculo> carregarVeiculos();
	public List<Viagem> carregarViagens();
	
	public void atualizarRegistroDeMotoristas(List<Motorista> motoristas);
	public void atualizarRegistroDeVeiculos(List<Veiculo> veiculos);
	public void atualizarRegistroDeViagens(List<Viagem> viagens);

}
