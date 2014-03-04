package org.deschutter.omen.skill;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.Before;
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

    private Skill skill;

	@Before
	public void setUp() {
		skill = new Skill();
		when(skillRepository.getSkillById(1L)).thenReturn(skill);
	}

	@Test
	public void getSkillById_returnsDTO() {
		assertThat(skillService.getSkillById(1L), isA(SkillDTO.class));
	}

	@Test
	public void getSkillById_returnsDTO_WithCorrectId() {
		skill.setId(1L);
		assertThat(skillService.getSkillById(1L), hasProperty("id", is(1L)));
	}

	@Test
	public void getSkillById_returnsDTO_WithCorrectName() {
		skill.setName("SkillName");
		assertThat(skillService.getSkillById(1L), hasProperty("name", is("SkillName")));
	}

	@Test
	public void getSkillById_returnsDTO_WithCorrectDescription() {
		skill.setDescription("SkillDescription");
		assertThat(skillService.getSkillById(1L), hasProperty("description", is("SkillDescription")));
	}
}
