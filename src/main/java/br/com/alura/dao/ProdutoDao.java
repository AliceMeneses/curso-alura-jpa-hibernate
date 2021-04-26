package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager entityManager;
	
	public ProdutoDao(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		
		entityManager.persist(produto);
	}
}
