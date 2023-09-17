package br.com.jpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory = null;

	static {
		// Verifica se a factory já foi criada antes de criar uma nova
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("estudoPrime");
		}
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {
		EntityManager em = getEntityManager();
		try {
			return em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
		} finally {
			em.close();
		}
	}

	public static Object getChavePrimaria(Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);

	}

	public static void closeEntityManagerFactory() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
	}

}
