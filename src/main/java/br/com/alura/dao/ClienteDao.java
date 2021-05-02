package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.modelo.Cliente;

public class ClienteDao {

	private EntityManager entityManager;
	
	public ClienteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrarCliente(Cliente cliente) {
		
		entityManager.persist(cliente);
	}
	
}
