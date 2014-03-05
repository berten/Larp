package org.deschutter.omen.skill;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class SkillRepositoryImplTest {
	@InjectMocks
	private SkillRepository skillRepository = new SkillRepositoryImpl();

	@Mock
	private SkillDao skillDao;

	@Mock
	private SkillFactory skillFactory;

	@Test
	public void getSkillById() {
        SkillEntity skill1 = new SkillEntity("name", "description");
        skill1.setId(1L);
        when(skillDao.findOne(1L)).thenReturn(skill1);
        StubbedSkill stubbedSkill = createStubbedSkillAndMockFactory(1L, "name", "description");
		assertEquals(stubbedSkill, skillRepository.getSkillById(1L));
	}

    @Test
    public void getAllSkills () {
        SkillEntity skill1 = new SkillEntity("name", "description") ;
        skill1.setId(1L);
        SkillEntity skill2=new SkillEntity("name2", "description2") ;
        skill2.setId(2L);
        when(skillDao.findAll()).thenReturn(Arrays.asList(skill1, skill2));


        StubbedSkill stubbedSkill = createStubbedSkillAndMockFactory(1L, "name", "description");
        StubbedSkill stubbedSkill2 = createStubbedSkillAndMockFactory(2L, "name2", "description2");

        assertThat(skillRepository.getAllSkills(),allOf(hasItem(stubbedSkill),hasItem(stubbedSkill2)));
    }

    private StubbedSkill createStubbedSkillAndMockFactory(long id, String name, String description) {
        StubbedSkill stubbedSkill = new StubbedSkill(id, name, description);
        when(skillFactory.create(id, name, description)).thenReturn(stubbedSkill);
        return stubbedSkill;
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
