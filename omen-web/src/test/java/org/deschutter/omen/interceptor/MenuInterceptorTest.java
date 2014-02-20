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
				Arrays.asList(new CharacterDTO(2L, "CharacterName1", "RaceName1"), new CharacterDTO(2L,
						"CharacterName2", "RaceName1")));
	}

	@Test
	public void index_NotLoggedIn_GetsBasicMenu() throws Exception {
		ResultActions index = mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"));
		index.andExpect(model().attribute(
                "menu",
                hasProperty(
                        "menuItems",
                        allOf(hasItemWithDisplayAndUrl("Home", "/index"),
                                hasItem(allOf(hasProperty("display", is("Epos")),
                                        hasProperty("url", isEmptyOrNullString()),
                                        hasProperty("menuItems", hasItemWithDisplayAndUrl("Algemeen", "/epos/algemeen")
                                        )))))));
	}

	@Test
	public void index_LoggedIn_GetsBasicMenu() throws Exception {
		when(characterService.getCharactersForUserID(123L)).thenReturn(
				Arrays.asList(new CharacterDTO(1L, "CharacterName1", "RaceName1"), new CharacterDTO(2L,
						"CharacterName2", "RaceName1")));
		ResultActions index =
				mockMvc.perform(get("/index").principal(new UsernamePasswordAuthenticationToken(user, "password")))
						.andExpect(status().isOk()).andExpect(view().name("index"));
		index.andExpect(model().attribute(
                "menu",
                hasProperty(
                        "menuItems",
                        allOf(hasItemWithDisplayAndUrl("Home", "/index"),
                                hasItem(allOf(
                                        hasProperty("display", is("Epos")),
                                        hasProperty("url", isEmptyOrNullString()),
                                        hasProperty(
                                                "menuItems",

                                                hasItem(allOf(
                                                        hasProperty("display", is("Karakters")),
                                                        hasProperty("url", isEmptyOrNullString()),
                                                        hasProperty(
                                                                "menuItems",
                                                                allOf(hasItemWithDisplayAndUrl("CharacterName1",
                                                                        "/epos/character/1"),
                                                                        hasItemWithDisplayAndUrl("CharacterName2",
                                                                                "/epos/character/2"))))))))))));
	}

	private Matcher<Iterable<? super Object>> hasBlankLine() {
		return hasItem(allOf(hasProperty("url", isEmptyOrNullString()), hasProperty("display", isEmptyOrNullString())));
	}

	private Matcher<Iterable<? super Object>> hasItemWithDisplayAndUrl(String display, String url) {
		return hasItem(allOf(hasProperty("display", is(display)), hasProperty("url", is(url))));
	}
}
