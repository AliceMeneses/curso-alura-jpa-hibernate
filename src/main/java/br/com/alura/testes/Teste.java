package br.com.alura.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.modelo.Produto;

public class Teste {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager entityManager = factory.createEntityManager();
		
		Produto celular = new Produto("Zenfone 5", "Cor preta", new BigDecimal(1000));
		
		entityManager.getTransaction().begin();
		entityManager.persist(celular);
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}

}
