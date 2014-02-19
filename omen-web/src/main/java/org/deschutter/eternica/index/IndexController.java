package org.deschutter.eternica.index;

import java.security.Principal;
import java.util.List;

import org.deschutter.authentication.user.User;
import org.deschutter.eternica.character.CharacterDTO;
import org.deschutter.eternica.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@Autowired
	private CharacterService characterService;

	@RequestMapping("/index")
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("index");
		if (principal != null) {
			User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
			List<CharacterDTO> characters = characterService.getCharactersForUserID(user.getUserId());
			modelAndView.addObject("characters", characters);
		}

		return modelAndView;
	}
}
