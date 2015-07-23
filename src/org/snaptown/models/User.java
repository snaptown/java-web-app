package org.snaptown.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "users")
@NamedQueries({
		@NamedQuery(name = "findUserByCredentials", query = "SELECT new org.snaptown.models.User(u.username, u.password, u.isAdmin)"
				+ " FROM users u WHERE u.username=:username AND u.password=:password"),
		@NamedQuery(name = "getUserDownVotes", query = "select count(s.scoreId) from users u join scores s  where s.voter = u.userId group by u.userId"),
		@NamedQuery(name = "findUserByUsername", query = "SELECT new org.snaptown.models.User(u.username, u.password, u.isAdmin)"
				+ " FROM users u WHERE u.username=:username") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(unique = true)
	private String username;

	private String password;
	private boolean isBlocked;
	private boolean isAdmin;

	public User() {

	}

	public User(final String username, final String password, final boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isBlocked = false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(getClass().getSimpleName());
		if (username != null && !username.trim().isEmpty())
			result.append(" username: ").append(username);
		if (password != null && !password.trim().isEmpty())
			result.append(", password: ").append(password);
		result.append(", isAdmin: ").append(Boolean.toString(isAdmin));
		result.append(", isBlocked: ").append(Boolean.toString(isBlocked));
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (userId != null) {
			if (!userId.equals(other.userId)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((userId == null) ? 0 : userId.hashCode());
	}

	public Long getId() {
		return userId;
	}
}
