package org.deschutter.eternica.race;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RACE")
public class RaceEntity {
    private String name;

    public RaceEntity(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
