package org.deschutter.omen.skill;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class, TestConfig.class })
@WebAppConfiguration
public class SkillControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private SkillService skillService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testCharacterView() throws Exception {
		SkillDTO skillDTO = new SkillDTO();
		when(skillService.getSkillById(1L)).thenReturn(skillDTO);
		mockMvc.perform(get("/omen/skill/1")).andExpect(status().isOk()).andExpect(view().name("omen/skill/detail"))
				.andExpect(model().attribute("skill", is(skillDTO)));
	}

    @Test
    public void list() throws Exception {
        SkillDTO skillDTO = new SkillDTO();
        SkillDTO skillDTO2 = new SkillDTO();
        when(skillService.getAllSkills()).thenReturn(Arrays.asList(skillDTO,skillDTO2));
        mockMvc.perform(get("/omen/skill/list")).andExpect(status().isOk()).andExpect(view().name("omen/skill/list"))
                .andExpect(model().attribute("skills", allOf(hasItem(skillDTO), hasItem(skillDTO2))));
    }
}
