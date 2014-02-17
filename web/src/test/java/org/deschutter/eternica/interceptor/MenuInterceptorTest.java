package org.deschutter.eternica.interceptor;

import org.deschutter.eternica.TestConfig;
import org.deschutter.eternica.init.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, TestConfig.class})
@WebAppConfiguration
public class MenuInterceptorTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void index_NotLoggedIn_GetsBasicMenu() throws Exception {
        ResultActions index = mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"));
        index
                .andExpect(
                        model().attribute("menu", hasProperty("menuItems",
                                allOf(
                                        hasItem(hasProperty("display", is("Home"))),
                                        hasItem(
                                                allOf(
                                                        hasProperty("display", is("Epos"))
                                                        , hasProperty("menuItems",
                                                        allOf(
                                                                hasItem(hasProperty("display", is("Algemeen"))),
                                                                hasItem(hasProperty("display", is("Basisdocumenten"))),
                                                                hasItem(hasProperty("display", is("Eternipedia")))
                                                        )
                                                )
                                                )
                                        ),
                                        hasItem(hasProperty("display", is("Iron Fist")))
                                )
                        )));
    }
}
