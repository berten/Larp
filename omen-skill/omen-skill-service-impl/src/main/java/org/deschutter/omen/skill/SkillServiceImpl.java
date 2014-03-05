package org.deschutter.omen.skill;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public SkillDTO getSkillById(Long skillId) {
		return new ModelMapper().map(skillRepository.getSkillById(skillId), SkillDTO.class);
	}

	@Override
	public List<SkillDTO> getAllSkills() {
		List<Skill> skills = skillRepository.getAllSkills();
		ArrayList<SkillDTO> skillDTOs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Skill skill : skills) {
            skillDTOs.add(modelMapper.map(skill, SkillDTO.class));
		}
		return skillDTOs;
	}
}
