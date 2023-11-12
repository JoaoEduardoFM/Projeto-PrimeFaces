package br.com.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.jpaUtil.JPAUtil;
import br.com.model.Pessoa;

public class IDaoPessoaimpl implements IDaoPessoa{

	@Override
	public Pessoa consultarUsuario(String login, String senha) {
	    Pessoa pessoa = null;
	    EntityManager entityManager = JPAUtil.getEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    
	    try {
	        entityTransaction.begin();
	        Query query = entityManager.createNativeQuery("SELECT * FROM Pessoa p WHERE p.login = ? and p.senha = ?", Pessoa.class);
	        query.setParameter(1, login);
	        query.setParameter(2, senha);

	        pessoa = (Pessoa) query.getSingleResult();

	        entityTransaction.commit();
	    } catch (NoResultException e) {
	        // Tratar o caso em que nenhum resultado é encontrado
	    } finally {
	        if (entityTransaction.isActive()) {
	            entityTransaction.rollback();
	        }
	        entityManager.close();
	    }

	    return pessoa;
	}


}
