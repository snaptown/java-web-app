package org.snaptown.dao;

import java.security.MessageDigest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.snaptown.models.User;

public class UserDAO extends AbstractDAO {

	public UserDAO(EntityManager em) {
		super(em);
	}

	public void addUser(User user) {
		user.setPassword(getHashedPassword(user.getPassword()));

		EntityTransaction addTransaction = beginTransaction();
		getEntityManager().persist(user);
		commitTransaction(addTransaction);
	}

	public User getUserByCredentials(final String username, final String password) {
		TypedQuery<User> query = getEntityManager().createNamedQuery("findUserByCredentials", User.class)
				.setParameter("username", username).setParameter("password", password);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
}
