package br.com.alura.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ClienteDao;
import br.com.alura.dao.PedidoDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Cliente;
import br.com.alura.modelo.ItemPedido;
import br.com.alura.modelo.Pedido;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class OperacoesPedido {

	public static void main(String[] args) {
		
		populaBancoDeDados();
		cadastrarPedido();
		calcularValorTotalDePedidos();
	}
	
	private static void calcularValorTotalDePedidos() {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		PedidoDao pedidoDao = new PedidoDao(entityManager);

		BigDecimal valorTotal = pedidoDao.valorTotalVendido();
		
		System.out.println("Valor total de pedidos: " + valorTotal);
	}

	private static void cadastrarPedido() {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		PedidoDao pedidoDao = new PedidoDao(entityManager);
		
		Cliente cliente = entityManager.find(Cliente.class, 1l);
		Produto produto = entityManager.find(Produto.class, 1l);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto));
		
		entityManager.getTransaction().begin();
		
		pedidoDao.cadastrarPedido(pedido);
		
		entityManager.getTransaction().commit();
				
	}
	
	private static void populaBancoDeDados() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(entityManager);
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);
		ClienteDao clienteDao = new ClienteDao(entityManager);

		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("Zenfone 5", "Cor preta", new BigDecimal(1000), celulares);

		Cliente cliente = new Cliente("Alice", "489745646");

		entityManager.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrarCliente(cliente);
		entityManager.getTransaction().commit();

		entityManager.close();
	}
}
