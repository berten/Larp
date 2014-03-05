package org.deschutter.omen.skill;

import org.springframework.stereotype.Component;

@Component
public class SkillFactoryImpl implements SkillFactory {
    @Override
    public Skill create(Long id, String name, String description) {
        return new SkillImpl(id,name,description);
    }
}
