package org.deschutter.omen.character;

import org.deschutter.omen.race.RaceEntity;
import org.deschutter.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "CHARACTER")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private UserEntity userEntity;

    private String name;

    @ManyToOne
    private RaceEntity race;


    public CharacterEntity(UserEntity userEntity, String name, RaceEntity race) {
        this.userEntity = userEntity;
        this.name = name;
        this.race = race;
    }

    public RaceEntity getRace() {
        return race;
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
