package org.deschutter.omen.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/omen/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;
    @RequestMapping("/list")
    public ModelAndView skillList() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return new ModelAndView("omen/skill/list","skills", skills);
    }

    @RequestMapping("/{skillId}")
    public ModelAndView characterView(@PathVariable Long skillId) {
        return new ModelAndView("omen/skill/detail","skill",skillService.getSkillById(skillId));
    }

}
