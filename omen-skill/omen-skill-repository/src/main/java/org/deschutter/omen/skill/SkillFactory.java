package org.deschutter.omen.skill;

public interface SkillFactory {
    Skill create(Long id, String name, String description);
}
