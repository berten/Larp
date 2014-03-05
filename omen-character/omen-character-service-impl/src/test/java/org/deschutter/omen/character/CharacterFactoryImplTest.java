package org.deschutter.omen.character;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterFactoryImplTest {
	@Test
	public void testCreate() throws Exception {
		Character result =
				new CharacterFactoryImpl().create(1L, "characterName", "lineageName", "className", "religionName",
						"wealthName");
		assertEquals(1L, result.getId().longValue());
		assertEquals("characterName", result.getCharacterName());
		assertEquals("lineageName", result.getLineageName());
		assertEquals("className", result.getClassName());
		assertEquals("religionName", result.getReligionName());
		assertEquals("wealthName", result.getWealthName());
	}
}
