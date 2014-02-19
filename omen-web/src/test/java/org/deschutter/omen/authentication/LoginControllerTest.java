package org.deschutter.omen.authentication;

import org.deschutter.omen.TestConfig;
import org.deschutter.omen.init.WebConfig;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, TestConfig.class})
@WebAppConfiguration
public class LoginControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"))
        /*
		 * .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp")) .andExpect(model().attribute("todos", hasSize(2)))
		 * .andExpect(model().attribute("todos", hasItem( allOf( hasProperty("id", is(1L)), hasProperty("description",
		 * is("Lorem ipsum")), hasProperty("title", is("Foo")) ) ))) .andExpect(model().attribute("todos", hasItem(
		 * allOf( hasProperty("id", is(2L)), hasProperty("description", is("Lorem ipsum")), hasProperty("title",
		 * is("Bar")) ) )))
		 */;

    }
}
