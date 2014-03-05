package org.deschutter.omen.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
