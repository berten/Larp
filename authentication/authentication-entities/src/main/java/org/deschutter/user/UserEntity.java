package org.deschutter.user;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


    private String username;

    private String password;

	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected UserEntity() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
