package br.com.alura.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class OperacoesProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		
		buscarProdutoPorId(1L);
		
		buscarTodosProdutos();
		
		buscarProdutoPorNome("Zenfone 5");
		
		buscarProdutoPorNomeDaCategoria("CELULARES");
		
		buscarPrecoDoProdutoPorNome("Zenfone 5");
		
	}
	
	private static void buscarPrecoDoProdutoPorNome(String nome) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		BigDecimal preco = produtoDao.buscarPrecoDoProdutoPorNome(nome);

		System.out.println("Preço do produto: " + preco);
	}

	private static void buscarProdutoPorNomeDaCategoria(String nome) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		List<Produto> produtos = produtoDao.buscarPorNomeDaCategoria(nome);

		produtos.forEach(System.out::println);
	}

	private static void buscarProdutoPorNome(String nome) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		List<Produto> produtos = produtoDao.buscarPorNome(nome);

		produtos.forEach(System.out::println);

	}

	private static void buscarTodosProdutos() {

		EntityManager entityManager = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		List<Produto> produtos = produtoDao.buscarTodos();

		produtos.forEach(System.out::println);
	}

	private static void buscarProdutoPorId(Long id) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(entityManager);

		Produto produto = produtoDao.buscarPorId(id);

		System.out.println(produto);
	}

	private static void cadastrarProduto() {
		EntityManager entityManager = JPAUtil.getEntityManager();

		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("Zenfone 5", "Cor preta", new BigDecimal(1000), celulares);

		ProdutoDao produtoDao = new ProdutoDao(entityManager);
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);

		entityManager.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		entityManager.getTransaction().commit();

		entityManager.close();
	}

}
