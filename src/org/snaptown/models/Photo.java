package org.snaptown.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

@Entity(name = "photos")
@NamedQueries({})
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Photo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long photo_id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User creator;

	private double longitude;
	private double latitude;
	private String comment;
	private String imgPath;
	private boolean isFixed;

	public Photo(final User creator, final String imgPath, final double longitude, final double latitude,
			final String comment) {
		super();
		this.creator = creator;
		this.imgPath = imgPath;
		this.longitude = longitude;
		this.latitude = latitude;
		this.comment = comment;
		this.isFixed = false;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
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
		return photo_id;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(getClass().getSimpleName());
		if (creator != null) {
			final String creatorName = creator.getUsername();
			if (creatorName != null && !creatorName.trim().isEmpty())
				result.append(" creator: ").append(creatorName);
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
		if (photo_id != null) {
			if (!photo_id.equals(other.photo_id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((photo_id == null) ? 0 : photo_id.hashCode());
	}
}
