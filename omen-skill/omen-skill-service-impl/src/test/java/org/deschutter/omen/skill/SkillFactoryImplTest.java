package org.deschutter.omen.skill;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SkillFactoryImplTest {

    @Test
    public void create () {
        Skill skill = new SkillFactoryImpl().create(1L, "name", "description");
        assertTrue(skill instanceof  SkillImpl);
        assertEquals(1L,skill.getId().longValue());
        assertEquals("name",skill.getName());
        assertEquals("description",skill.getDescription());
    }
}
