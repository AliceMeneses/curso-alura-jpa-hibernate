package br.com.alura.dao;

import java.math.BigDecimal;

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
	
	public BigDecimal valorTotalVendido() {
		
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
}
