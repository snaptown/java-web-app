package org.snaptown.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.snaptown.models.Photo;
import org.snaptown.models.Score;
import org.snaptown.models.User;

public class ScoreDAO extends AbstractDAO {

	public ScoreDAO(EntityManager em) {
		super(em);
	}

	public void addScoreForPhoto(final Photo photo, final User voter, boolean isUpvote) {
		Score score = new Score(voter, photo, isUpvote);
		EntityTransaction addTransaction = beginTransaction();
		getEntityManager().persist(score);
		commitTransaction(addTransaction);
	}
}
