package org.deschutter.eternica.character;

import org.deschutter.user.User;

import javax.persistence.*;

@Entity
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
@ManyToOne(cascade = CascadeType.REMOVE)
	private User user;

    private String name;

	public Character(User user, String name) {
		this.user = user;
        this.name = name;
    }

	protected Character() {

	}

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }
}
