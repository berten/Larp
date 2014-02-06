package org.deschutter.eternica.init;

import org.deschutter.eternica.character.Character;
import org.deschutter.eternica.character.CharacterRepository;
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

    @Autowired
    private CharacterRepository characterRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.deleteAll();
        User berten = new User("Berten", "pwBerten");
        userRepository.save(berten);
        User tim = new User("Tim", "pwTim");
        userRepository.save(tim);
        characterRepository.save(new Character(berten, "Nilus"));
        characterRepository.save(new Character(tim, "Bors"));
	}
}
