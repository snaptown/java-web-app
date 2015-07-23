package org.snaptown.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;

@Entity(name = "scores")
@NamedQueries({})
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long scoreId;

	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Long userId;

	@JoinColumn(name = "photoId", referencedColumnName = "photoId")
	private Long photoId;

	private boolean isUpvote;

	public Score() {

	}

	public Score(final Long userId, final Long photoId, final boolean isUpvote) {
		super();
		this.userId = userId;
		this.photoId = photoId;
		this.isUpvote = isUpvote;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public boolean isUpvote() {
		return isUpvote;
	}

	public void setUpvote(boolean isUpvote) {
		this.isUpvote = isUpvote;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(getClass().getSimpleName());
		if (userId != null) {
			result.append(" voter: ").append(userId);
		}
		if (photoId != null) {
			result.append(" photo: ").append(photoId);
		}
		result.append(", isUpvote: ").append(Boolean.toString(isUpvote));
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Score)) {
			return false;
		}
		Score other = (Score) obj;
		if (scoreId != null) {
			if (!scoreId.equals(other.scoreId)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((scoreId == null) ? 0 : scoreId.hashCode());
	}
}
