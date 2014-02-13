package org.deschutter.eternica.index;

import java.security.Principal;

import org.deschutter.eternica.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public ModelAndView index(Principal principal) {
		System.out.println(principal);
		return new ModelAndView("index");
	}
}
