package org.snaptown.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.snaptown.models.Photo;

public class PhotoDAO extends AbstractDAO {

	public PhotoDAO(EntityManager em) {
		super(em);
	}

	public void addPhoto(final Photo photo) {
		EntityTransaction addTransaction = beginTransaction();
		getEntityManager().persist(photo);
		commitTransaction(addTransaction);
	}

	public List<Photo> getPhotosByNewest() {
		TypedQuery<Photo> query = getEntityManager().createNamedQuery("getPhotosByNewest", Photo.class);
		return query.getResultList();
	}

	public List<Photo> getPhotosByMostLikes() {
		TypedQuery<Photo> query = getEntityManager().createNamedQuery("getPhotosByMostLiked", Photo.class);
		return query.getResultList();
	}

	public List<Photo> getAdminPhotos(final int topLimit) {
		List<Photo> mostLiked = getPhotosByMostLikes();
		List<Photo> result = new LinkedList<>();
		for (Photo photo : mostLiked) {
			if (!photo.isFixed()) {
				result.add(photo);
				if (result.size() == topLimit) {
					return result;
				}
			}
		}
		return result;
	}

	public Photo getPhotoById(final Long photoId) {
		return getEntityManager().find(Photo.class, photoId);
	}
}
