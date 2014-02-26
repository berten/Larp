package org.deschutter.omen.interceptor;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.deschutter.authentication.user.User;
import org.deschutter.omen.TestConfig;
import org.deschutter.omen.character.CharacterDTO;
import org.deschutter.omen.character.CharacterService;
import org.deschutter.omen.init.WebConfig;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class, TestConfig.class })
@WebAppConfiguration
public class MenuInterceptorTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	CharacterService characterService;
	private User user;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		user = mock(User.class);
		when(user.getUserId()).thenReturn(123L);
		when(characterService.getCharactersForUserID(123L)).thenReturn(
				Arrays.asList(
						new CharacterDTO(2L, "CharacterName1", "Lineage1", "Class", "ReligionName", "WealthName"),
						new CharacterDTO(2L, "CharacterName2", "Lineage1", "Class", "ReligionName", "WealthName")));
	}

	@Test
	public void index_NotLoggedIn_GetsBasicMenu() throws Exception {
		ResultActions index = mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"));
		index.andExpect(model().attribute(
				"menu",
				hasProperty(
						"menuItems",
						allOf(hasHomeMenu(),
								hasItemWithDisplayAndMenuItems("Ingame",
										hasItemWithDisplayAndUrl("Volkeren", "/ingame/lineage"),
										hasItemWithDisplayAndUrl("Klassen", "/ingame/classes"),
										hasItemWithDisplayAndUrl("Heimar", "/ingame/heimar"),
										hasItemWithDisplayAndUrl("Organisaties", "/ingame/organisations"),
										hasItemWithDisplayAndUrl("Vijanden", "/ingame/enemies"),
										hasItemWithDisplayAndUrl("Kalender", "/ingame/calendar"),
										hasItemWithDisplayAndUrl("Taal", "/ingame/language")), hasOutGameMenu(),
								hasContactMenu()))));
	}

	@Test
	public void index_LoggedIn_GetsBasicMenu() throws Exception {
		when(characterService.getCharactersForUserID(123L)).thenReturn(
				Arrays.asList(
						new CharacterDTO(1L, "CharacterName1", "Lineage1", "Class", "ReligionName", "WealthName"),
						new CharacterDTO(2L, "CharacterName2", "Lineage1", "Class", "ReligionName", "WealthName")));
		ResultActions index =
				mockMvc.perform(get("/index").principal(new UsernamePasswordAuthenticationToken(user, "password")))
						.andExpect(status().isOk()).andExpect(view().name("index"));

		index.andExpect(model().attribute("menu",
                hasProperty(
                        "menuItems",
                        allOf(hasHomeMenu(),
                                hasItemWithDisplayAndMenuItems("Ingame",
                                        hasItemWithDisplayAndUrl("Volkeren", "/ingame/lineage"),
                                        hasItemWithDisplayAndUrl("Klassen", "/ingame/classes"),
                                        hasItemWithDisplayAndUrl("Heimar", "/ingame/heimar"),
                                        hasItemWithDisplayAndUrl("Organisaties", "/ingame/organisations"),
                                        hasItemWithDisplayAndUrl("Vijanden", "/ingame/enemies"),
                                        hasItemWithDisplayAndUrl("Kalender", "/ingame/calendar"),
                                        hasItemWithDisplayAndUrl("Taal", "/ingame/language"), hasBlankLine(),
                                        hasCharacterMenu()
                                )
                                , hasOutGameMenu(),
                                hasContactMenu()))));
	}

	private Matcher<Iterable<? super Object>> hasContactMenu() {
		return hasItemWithDisplayAndUrl("Contact", "/contact");
	}

	private Matcher<? super Iterable<? super Object>> hasOutGameMenu() {
		return hasItemWithDisplayAndMenuItems("Outgame", hasItemWithDisplayAndUrl("Larp", "/outgame/larp"),
				hasItemWithDisplayAndUrl("Richtlijnen", "/outgame/rules"),
				hasItemWithDisplayAndUrl("Spelershandboek", "/outgame/manual"),
				hasItemWithDisplayAndUrl("Forum", "/outgame/forum"),
				hasItemWithDisplayAndUrl("Evenementen", "/outgame/events"));
	}

	private Matcher<? super Iterable<? super Object>> hasCharacterMenu() {
		return hasItemWithDisplayAndMenuItems("Karakters",
				hasItemWithDisplayAndUrl("CharacterName1", "/epos/character/1"),
				hasItemWithDisplayAndUrl("CharacterName2", "/epos/character/2"));
	}

	private Matcher<Iterable<? super Object>> hasHomeMenu() {
		return hasItemWithDisplayAndUrl("Home", "/index");
	}

	private Matcher<? super Iterable<? super Object>> hasItemWithDisplayAndMenuItems(String display,
			Matcher... matchers) {
		return hasItem(allOf(hasProperty("display", is(display)), hasProperty("url", isEmptyOrNullString()),
				hasProperty("menuItems", allOf(matchers))));
	}

	private Matcher<Iterable<? super Object>> hasBlankLine() {
		return hasItem(allOf(hasProperty("url", isEmptyOrNullString()), hasProperty("display", isEmptyOrNullString())));
	}

	private Matcher<Iterable<? super Object>> hasItemWithDisplayAndUrl(String display, String url) {
		return hasItem(allOf(hasProperty("display", is(display)), hasProperty("url", is(url))));
	}
}
