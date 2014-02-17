package org.deschutter.eternica.interceptor;

import org.deschutter.authentication.user.User;
import org.deschutter.eternica.TestConfig;
import org.deschutter.eternica.character.Character;
import org.deschutter.eternica.character.CharacterService;
import org.deschutter.eternica.init.WebConfig;
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

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, TestConfig.class})
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
        when(characterService.getCharactersForUserID(123L)).thenReturn(Arrays.asList(new org.deschutter.eternica.character.Character(2L, "CharacterName1"), new Character(2L, "CharacterName2")));
    }

    @Test
    public void index_NotLoggedIn_GetsBasicMenu() throws Exception {
        ResultActions index = mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"));
        index
                .andExpect(
                        model().attribute("menu", hasProperty("menuItems",
                                allOf(
                                        hasItem(allOf(hasProperty("display", is("Home")), hasProperty("url", is("/index")))),
                                        hasItem(
                                                allOf(
                                                        hasProperty("display", is("Epos"))
                                                        , hasProperty("url", isEmptyOrNullString())
                                                        , hasProperty("menuItems",
                                                        allOf(
                                                                hasItem(allOf(hasProperty("display", is("Algemeen")), hasProperty("url", is("/epos/algemeen")))),
                                                                hasItem(allOf(hasProperty("display", is("Basisdocumenten")), hasProperty("url", is("/epos/basisdocumenten")))),
                                                                hasItem(allOf(hasProperty("url", isEmptyOrNullString()), hasProperty("display", isEmptyOrNullString()))),
                                                                hasItem(allOf(hasProperty("display", is("Eternipedia")), hasProperty("url", is("http://eternipedia.eternica.com"))))
                                                        )
                                                )
                                                )
                                        ),
                                        hasItem(allOf(hasProperty("display", is("Iron Fist")), hasProperty("url", is("http://ironfist.eternica.com"))))
                                )
                        )));
    }

    @Test
    public void index_LoggedIn_GetsBasicMenu() throws Exception {
        when(characterService.getCharactersForUserID(123L)).thenReturn(Arrays.asList(new Character(1L, "CharacterName1"), new Character(2L, "CharacterName2")));
        ResultActions index = mockMvc.perform(get("/index").principal(new UsernamePasswordAuthenticationToken(user, "password"))).andExpect(status().isOk()).andExpect(view().name("index"));
        index
                .andExpect(
                        model().attribute("menu", hasProperty("menuItems",
                                allOf(
                                        hasItem(allOf(hasProperty("display", is("Home")), hasProperty("url", is("/index")))),

                                        hasItem(
                                                allOf(
                                                        hasProperty("display", is("Epos"))
                                                        , hasProperty("url", isEmptyOrNullString())
                                                        , hasProperty("menuItems",
                                                        allOf(
                                                                hasItem(allOf(hasProperty("display", is("Algemeen")), hasProperty("url", is("/epos/algemeen")))),
                                                                hasItem(allOf(hasProperty("display", is("Basisdocumenten")), hasProperty("url", is("/epos/basisdocumenten")))),
                                                                hasItem(allOf(hasProperty("url", isEmptyOrNullString()), hasProperty("display", isEmptyOrNullString()))),
                                                                hasItem(allOf(hasProperty("display", is("Karakters")), hasProperty("url", isEmptyOrNullString()), hasProperty("menuItems", allOf(hasItem(allOf(hasProperty("display", is("CharacterName1")), hasProperty("url", is("/epos/character/1")))), hasItem(allOf(hasProperty("display", is("CharacterName2")), hasProperty("url", is("/epos/character/2")))))))),
                                                                hasItem(allOf(hasProperty("url", isEmptyOrNullString()), hasProperty("display", isEmptyOrNullString()))),
                                                                hasItem(allOf(hasProperty("display", is("Eternipedia")), hasProperty("url", is("http://eternipedia.eternica.com"))))
                                                        )
                                                )
                                                )
                                        ),
                                        hasItem(allOf(hasProperty("display", is("Iron Fist")), hasProperty("url", is("http://ironfist.eternica.com"))))
                                )
                        )));
    }
}
