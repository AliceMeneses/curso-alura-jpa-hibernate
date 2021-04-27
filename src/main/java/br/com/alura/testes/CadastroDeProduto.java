package br.com.alura.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		
		Produto celular = new Produto("Zenfone 5", "Cor preta", new BigDecimal(1000), Categoria.CELULARES);
		
		ProdutoDao produtoDao = new ProdutoDao(entityManager);
		
		entityManager.getTransaction().begin();
		produtoDao.cadastrar(celular);
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}

}
