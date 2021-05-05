package br.com.alura.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.modelo.Pedido;
import br.com.alura.vo.RelatorioDeVendasVo;

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
	
	public List<RelatorioDeVendasVo> relatorioVendas(){
		
		String jpql = "SELECT new br.com.alura.vo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return entityManager.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
}
