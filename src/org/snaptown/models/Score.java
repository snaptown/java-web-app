package org.snaptown.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

@Entity(name = "scores")
@NamedQueries({})
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long scoreId;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User voter;

	@ManyToOne
	@JoinColumn(name = "photoId", referencedColumnName = "photoId")
	private Photo photo;

	private boolean isUpvote;

	public Score() {

	}

	public Score(final User voter, final Photo photo, final boolean isUpvote) {
		super();
		this.voter = voter;
		this.photo = photo;
		this.isUpvote = isUpvote;
	}

	public User getVoter() {
		return voter;
	}

	public void setVoter(User voter) {
		this.voter = voter;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
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
		if (voter != null) {
			final String voterName = voter.getUsername();
			if (voterName != null && !voterName.trim().isEmpty())
				result.append(" voter: ").append(voterName);
		}
		if (photo != null) {
			final Long photoId = photo.getId();
			result.append(" photo: ").append(photoId == null ? 0 : photoId);
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
