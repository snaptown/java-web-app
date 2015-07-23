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

	public long addUser(User user) {
		user.setPassword(getHashedPassword(user.getPassword()));

		EntityTransaction addTransaction = beginTransaction();
		getEntityManager().persist(user);
		commitTransaction(addTransaction);
		return user.getId();
	}

	public User getUserByCredentials(final String username, final String password) {
		TypedQuery<User> query = getEntityManager().createNamedQuery("findUserByCredentials", User.class)
				.setParameter("username", username).setParameter("password", getHashedPassword(password));
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User getUserByUsername(final String username) {
		TypedQuery<User> query = getEntityManager().createNamedQuery("findUserByUsername", User.class)
				.setParameter("username", username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean blockUserIfTooManyDownVotes(long userId, int downVotesLimit) {
		TypedQuery<Integer> query = getEntityManager().createNamedQuery("getUserDownVotes", Integer.class);
		if (query.getSingleResult() > downVotesLimit) {
			EntityTransaction addTransaction = beginTransaction();
			User userToBlock = getUserById(userId);
			userToBlock.setBlocked(true);
			commitTransaction(addTransaction);
			return true;
		}
		return false;
	}

	public User getUserById(final Long userId) {
		return getEntityManager().find(User.class, userId);
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
