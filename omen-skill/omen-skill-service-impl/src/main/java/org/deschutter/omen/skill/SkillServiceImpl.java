package org.deschutter.omen.skill;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDTO getSkillById(Long skillId) {
        return new ModelMapper().map(skillRepository.getSkillById(skillId),SkillDTO.class);
    }
}
