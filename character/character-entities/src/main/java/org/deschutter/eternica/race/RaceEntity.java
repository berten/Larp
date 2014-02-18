package org.deschutter.eternica.race;


import javax.persistence.*;

@Entity
@Table(name = "RACE")
public class RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public RaceEntity() {
    }

    public RaceEntity(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
