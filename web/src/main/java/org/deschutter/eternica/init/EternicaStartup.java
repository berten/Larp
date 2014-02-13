package org.deschutter.eternica.init;

import org.deschutter.eternica.character.Character;
import org.deschutter.eternica.character.CharacterRepository;
import org.deschutter.user.UserEntity;
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
		characterRepository.deleteAll();
		userRepository.deleteAll();
		UserEntity berten = new UserEntity("Berten", "pwBerten");
		userRepository.save(berten);
		UserEntity tim = new UserEntity("Tim", "pwTim");
		userRepository.save(tim);
		characterRepository.save(new Character(berten, "Nilus"));
		characterRepository.save(new Character(tim, "Bors"));
	}
}
