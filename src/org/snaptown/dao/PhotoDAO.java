package org.snaptown.dao;

import javax.persistence.EntityManager;

public class PhotoDAO extends AbstractDAO {

	public PhotoDAO(EntityManager em) {
		super(em);
	}
}
