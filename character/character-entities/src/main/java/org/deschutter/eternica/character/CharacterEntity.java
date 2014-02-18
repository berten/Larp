package org.deschutter.eternica.character;

import org.deschutter.user.UserEntity;
import org.dozer.Mapping;

import javax.persistence.*;

@Entity
@Table(name = "CHARACTER")
public class CharacterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private UserEntity userEntity;

    @Mapping("characterName")
	private String name;


	public CharacterEntity(UserEntity userEntity, String name) {
		this.userEntity = userEntity;
		this.name = name;
	}

    protected CharacterEntity() {
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterEntity that = (CharacterEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}
