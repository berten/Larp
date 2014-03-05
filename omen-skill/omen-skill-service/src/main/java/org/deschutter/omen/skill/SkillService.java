package org.deschutter.omen.skill;


import java.util.List;

public interface SkillService {
    SkillDTO getSkillById(Long skillId);

    List<SkillDTO> getAllSkills();
}
