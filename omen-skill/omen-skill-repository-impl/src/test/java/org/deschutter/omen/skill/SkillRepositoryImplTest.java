package org.deschutter.omen.skill;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SkillRepositoryImplTest {
	@InjectMocks
	private SkillRepository skillRepository = new SkillRepositoryImpl();

	@Mock
	private SkillDao skillDao;

	@Mock
	private SkillFactory skillFactory;

	@Test
	public void blaat() {
        when(skillDao.findOne(1L)).thenReturn(new SkillEntity(1L, "name", "description"));
		StubbedSkill stubbedSkill = new StubbedSkill(1L, "name", "description");
		when(skillFactory.create(1L, "name", "description")).thenReturn(stubbedSkill);
		assertEquals(stubbedSkill, skillRepository.getSkillById(1L));
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
