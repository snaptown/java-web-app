package org.snaptown.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.snaptown.models.Score;

public class ScoreDAO extends AbstractDAO {

	public ScoreDAO(EntityManager em) {
		super(em);
	}

	public void addScoreForPhoto(final Long photoId, final Long userId, boolean isUpvote) {
		Score score = new Score(userId, photoId, isUpvote);
		EntityTransaction addTransaction = beginTransaction();
		getEntityManager().persist(score);
		commitTransaction(addTransaction);
	}
}
