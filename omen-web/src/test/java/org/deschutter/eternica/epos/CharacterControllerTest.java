package org.deschutter.eternica.epos;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.deschutter.eternica.TestConfig;
import org.deschutter.eternica.character.CharacterDTO;
import org.deschutter.eternica.character.CharacterService;
import org.deschutter.eternica.init.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class, TestConfig.class })
@WebAppConfiguration
public class CharacterControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private CharacterService characterService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testCharacterView_LoggedIn_HasUserAccess_AccessGranted() throws Exception {
		when(characterService.getCharacter(1L)).thenReturn(new CharacterDTO(1L, "CharacterName1","RaceName1"));
		mockMvc.perform(get("/epos/character/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("epos/character"))
				.andExpect(
                        model().attribute("character",
                                allOf(
                                        hasProperty("id", is(1L)),
                                        hasProperty("characterName", is("CharacterName1")),
                                        hasProperty("raceName",is("RaceName1"))
                                )));
	}
}
