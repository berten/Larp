package org.deschutter.omen.character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public ClassEntity(String name) {
        this.name = name;
    }


    public ClassEntity() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
