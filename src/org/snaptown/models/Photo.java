package org.snaptown.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "photos")
@NamedQueries({
		@NamedQuery(name = "getPhotosByNewest", query = "select new org.snaptown.models.Photo(p.userId, p.imgPath, p.longitude, p.latitude, p.comment) from photos p order by p.dateCreated desc"),
		@NamedQuery(name = "getPhotosByMostLiked", query = "select new org.snaptown.models.Photo(p.userId, p.imgPath, p.longitude, p.latitude, p.comment)"
				+ " from photos p join scores s where s.photo = p.photoId and s.isUpvote = true group by p.photoId order by count(s.scoreId) desc") })
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Photo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long photoId;

	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	private double longitude;
	private double latitude;
	private String comment;
	private String imgPath;
	private boolean isFixed;

	public Photo(final Long userId, final String imgPath, final double longitude, final double latitude,
			final String comment) {
		super();
		this.userId = userId;
		this.imgPath = imgPath;
		this.longitude = longitude;
		this.latitude = latitude;
		this.comment = comment;
		this.isFixed = false;
	}

	@PrePersist
	protected void onCreate() {
		dateCreated = new Date();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	public Long getId() {
		return photoId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(getClass().getSimpleName());
		if (userId != null) {
			result.append(" creator: ").append(userId);
		}
		if (dateCreated != null) {
			result.append(", date-created: ").append(dateCreated.toString());
		}
		if (imgPath != null && !imgPath.trim().isEmpty())
			result.append(", image-path: ").append(imgPath);
		result.append(", longitude: ").append(longitude);
		result.append(", latitude: ").append(latitude);
		if (comment != null && !comment.trim().isEmpty())
			result.append(", comment: ").append(comment);
		result.append(", isFixed: ").append(Boolean.toString(isFixed));
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Photo)) {
			return false;
		}
		Photo other = (Photo) obj;
		if (photoId != null) {
			if (!photoId.equals(other.photoId)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((photoId == null) ? 0 : photoId.hashCode());
	}
}
