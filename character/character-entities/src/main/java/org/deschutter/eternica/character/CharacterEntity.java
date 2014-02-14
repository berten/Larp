package org.deschutter.eternica.character;

import javax.persistence.*;

import org.deschutter.user.UserEntity;

@Entity
@Table(name = "CHARACTER")
public class CharacterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private UserEntity userEntity;

	private String name;

	public CharacterEntity(UserEntity userEntity, String name) {
		this.userEntity = userEntity;
		this.name = name;
	}
}
