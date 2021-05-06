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

public class PerformanceConsultas {

	public static void main(String[] args) {
		
		populaBancoDeDados();
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		PedidoDao pedidoDao = new PedidoDao(entityManager);
		
		Pedido pedido = pedidoDao.buscarPorId(1l);
		
		System.out.println("Data do pedido: " + pedido.getData());
		System.out.println("Cliente: " + pedido.getCliente().getNome());
		System.out.println("Quantidade de itens no pedido: " + pedido.getItens().size());

	}
	
	public static void populaBancoDeDados() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(entityManager);
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);
		ClienteDao clienteDao = new ClienteDao(entityManager);
		PedidoDao pedidoDao = new PedidoDao(entityManager);

		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("Zenfone 5", "Cor preta", new BigDecimal(1000), celulares);

		Cliente cliente = new Cliente("Alice", "489745646");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(3, pedido, celular));

		entityManager.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrarCliente(cliente);
		pedidoDao.cadastrarPedido(pedido);
		entityManager.getTransaction().commit();

		entityManager.close();
	}

}
