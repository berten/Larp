package org.deschutter.omen.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping("/omen/skill/{skillId}")
    public ModelAndView characterView(@PathVariable Long skillId) {
        return new ModelAndView("omen/skill","skill",skillService.getSkillById(skillId));
    }

}
