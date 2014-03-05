package org.deschutter.omen.skill;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
