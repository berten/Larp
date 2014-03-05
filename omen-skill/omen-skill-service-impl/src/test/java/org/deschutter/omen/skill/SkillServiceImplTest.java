package org.deschutter.omen.skill;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {

	@InjectMocks
	private SkillService skillService = new SkillServiceImpl();

	@Mock
	private SkillRepository skillRepository;

	@Test
	public void getSkillById_returnsDTO() {
		Skill skill = new StubbedSkill(1L, "SkillName", "SkillDescription");
		when(skillRepository.getSkillById(1L)).thenReturn(skill);
		assertThat(skillService.getSkillById(1L), matchingDto(1L, "SkillName", "SkillDescription"));
	}

    @Test
    public void getAllSkills_returnsListOfDTOS() {
        Skill skill1 = new StubbedSkill(1L, "SkillName", "SkillDescription");
        Skill skill2 = new StubbedSkill(2L, "SkillName2", "SkillDescription2");
        when(skillRepository.getAllSkills()).thenReturn(Arrays.asList(skill1, skill2));
        assertThat(
                skillService.getAllSkills(),
                allOf(
                        hasItem(matchingDto(1L, "SkillName", "SkillDescription")),
                        hasItem(matchingDto(2L, "SkillName2", "SkillDescription2"))
                )
        );
    }

	private Matcher<SkillDTO> matchingDto(long id, String skillName, String skillDescription) {
		return allOf(isA(SkillDTO.class), hasProperty("id", is(id)), hasProperty("name", is(skillName)),
				hasProperty("description", is(skillDescription)));
	}

    private class StubbedSkill implements Skill {
        private final Long id;
        private final String name;
        private final String description;

        public StubbedSkill(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
