package org.deschutter.omen.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @RequestMapping("/omen/character/{characterId}")
    public ModelAndView characterView(@PathVariable Long characterId) {
        CharacterDTO character = characterService.getCharacter(characterId);
        return new ModelAndView("omen/character", "character", character);
    }
}
