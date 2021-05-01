package br.com.alura.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		
		buscarProduto();
		
		buscarTodosProdutos();
	}
	
	private static void buscarTodosProdutos() {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(entityManager);
				
		List<Produto> produtos = produtoDao.buscarTodos();
		
		produtos.forEach(System.out::println);
	}

	private static void buscarProduto() {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
				
		ProdutoDao produtoDao = new ProdutoDao(entityManager);
				
		Produto produto = produtoDao.buscarPorId(1L);
		
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
