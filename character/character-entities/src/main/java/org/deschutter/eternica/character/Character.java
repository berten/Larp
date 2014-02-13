package org.deschutter.eternica.character;

import javax.persistence.*;

import org.deschutter.user.UserEntity;

@Entity
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private UserEntity userEntity;

	private String name;

	public Character(UserEntity userEntity, String name) {
		this.userEntity = userEntity;
		this.name = name;
	}

	protected Character() {

	}

	public long getId() {
		return id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public String getName() {
		return name;
	}
}
