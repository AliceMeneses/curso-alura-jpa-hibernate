package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.modelo.Categoria;

public class CategoriaDao {

	private EntityManager entityManager;
	
	public CategoriaDao(EntityManager entityManager) {
		
		this.entityManager = entityManager; 
	}
	
	public void cadastrar(Categoria categoria) {
		
		entityManager.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.entityManager.merge(categoria);
	}
	
	public void deletar(Categoria categoria) {
		categoria = this.entityManager.merge(categoria);
		this.entityManager.remove(categoria);
	}
}
