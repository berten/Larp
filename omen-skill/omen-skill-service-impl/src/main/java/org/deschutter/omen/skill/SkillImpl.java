package org.deschutter.omen.skill;

public class SkillImpl implements Skill {
    private Long id;
    private String name;
    private String description;

    public SkillImpl(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
