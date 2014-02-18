package org.deschutter.eternica.epos;

import org.deschutter.eternica.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @RequestMapping("/epos/character/{characterId}")
    public ModelAndView characterView(@PathVariable Long characterId) {
        return new ModelAndView("epos/character","character",characterService.getCharacter(characterId));
    }
}
