package org.deschutter.eternica.index;

import org.deschutter.User;
import org.deschutter.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public ModelAndView helloWorld() {
        userRepository.save(new User("Berten", "pwBerten"));
        userRepository.save(new User("Tim", "pwTim"));
        return new ModelAndView("index", "users", userRepository.findAll());
    }
}
