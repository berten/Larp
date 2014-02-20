package org.deschutter.omen.lineage;


import javax.persistence.*;

@Entity
@Table(name = "RACE")
public class LineageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public LineageEntity() {
    }

    public LineageEntity(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
