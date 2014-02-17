package org.deschutter.eternica.index;

import org.deschutter.authentication.user.User;
import org.deschutter.eternica.character.Character;
import org.deschutter.eternica.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private CharacterService characterService;

	@RequestMapping("/index")
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("index");
		if (principal != null) {
			User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
			List<Character> characters = characterService.getCharactersForUserID(user.getUserId());
			modelAndView.addObject("characters", mapToDTO(characters));
		}

		return modelAndView;
	}

    private List<CharacterDTO> mapToDTO(List<Character> characters) {
        ArrayList<CharacterDTO> characterDTOs = new ArrayList<>();
        for (Character character : characters) {
		characterDTOs.add(new CharacterDTO(character.getCharacterName()));
		}
        return characterDTOs;
    }

    public class CharacterDTO {

        private String characterName;

        public CharacterDTO(String characterName) {

            this.characterName = characterName;
        }

        public String getCharacterName() {
            return characterName;
        }
    }
}
