package org.deschutter.eternica.init;

import org.deschutter.user.User;
import org.deschutter.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class EternicaStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.deleteAll();
		userRepository.save(new User("Berten", "pwBerten"));
		userRepository.save(new User("Tim", "pwTim"));
	}
}
