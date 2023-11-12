package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpaUtil.JPAUtil;

public class DaoGeneric<T> {

	// salva valores no banco de dados
	public void salvar(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		// inicita transação
		entityTransaction.begin();

		// persisti transacao
		entityManager.persist(entidade);

		// commita transação
		entityTransaction.commit();

		// fecha transação
		entityManager.close();

	}

	// retorna valores da entidade
	public T merge(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		T retorno = entityManager.merge(entidade);

		entityTransaction.commit();
		entityManager.close();
		return retorno;

	}

	public void delete(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		entityManager.remove(entidade);

		entityTransaction.commit();
		entityManager.close();

	}

	public void deletePorId(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		Object id = JPAUtil.getChavePrimaria(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " WHERE id = " + id)
				.executeUpdate();
		entityTransaction.commit();
		entityManager.close();

	}

	public List<T> listaEntidade(Class<T> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		@SuppressWarnings("unchecked")
		List<T> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

		entityTransaction.commit();
		entityManager.close();

		return retorno;

	}
}
