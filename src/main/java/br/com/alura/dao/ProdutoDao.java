package br.com.alura.dao;

import java.util.List;

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
	
	public Produto buscarPorId(Long id) {
		return entityManager.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql =  "SELECT p FROM Produto p";
		
		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}
}
