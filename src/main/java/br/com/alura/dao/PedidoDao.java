package br.com.alura.dao;

import javax.persistence.EntityManager;

import br.com.alura.modelo.Pedido;

public class PedidoDao {

	private EntityManager entityManager;
	
	public PedidoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrarPedido(Pedido pedido) {
		
		entityManager.persist(pedido);
	}
	
}
