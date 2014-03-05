package org.deschutter.omen.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SkillRepositoryImpl implements SkillRepository {
    @Autowired
    private SkillDao skillDao;

    @Autowired
    private SkillFactory skillFactory;

    @Override
    public Skill getSkillById(Long id) {
        SkillEntity entity = skillDao.findOne(id);
        return skillFactory.create(id,entity.getName(),entity.getDescription());
    }

    @Override
    public List<Skill> getAllSkills() {
        Iterable<SkillEntity> skills = skillDao.findAll();
        ArrayList<Skill> returnList = new ArrayList<>();
        for (SkillEntity skill : skills) {
            returnList.add(skillFactory.create(skill.getId(),skill.getName(),skill.getDescription()));
        }
        return returnList;
    }
}
