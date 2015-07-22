package org.snaptown.providers;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProvider {
	private static final String PERSISTENCE_UNIT = "snaptown";

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
	}
}