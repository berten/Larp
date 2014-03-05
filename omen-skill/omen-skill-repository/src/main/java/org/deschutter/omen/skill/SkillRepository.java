package org.deschutter.omen.skill;

import java.util.List;

public interface SkillRepository {

    Skill getSkillById(Long id);

    List<Skill> getAllSkills();
}
