package org.deschutter.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String username;
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected User() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
