package org.deschutter.eternica.index;

import java.security.Principal;

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
