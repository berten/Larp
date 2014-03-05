package org.deschutter.omen.skill;

import javax.persistence.*;

@Entity
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 4000)
    private String description;
    private String name;

    public SkillEntity() {
    }

    public SkillEntity(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
