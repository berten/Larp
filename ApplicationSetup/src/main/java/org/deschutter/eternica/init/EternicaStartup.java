package org.deschutter.eternica.init;

import org.deschutter.eternica.character.CharacterEntity;
import org.deschutter.eternica.character.CharacterRepository;
import org.deschutter.user.UserDao;
import org.deschutter.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class EternicaStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UserDao userDao;

	@Autowired
	private CharacterRepository characterRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		characterRepository.deleteAll();
		userDao.deleteAll();
		UserEntity berten = new UserEntity("Berten", "pwBerten");
		userDao.save(berten);
		UserEntity tim = new UserEntity("Tim", "pwTim");
		userDao.save(tim);
		characterRepository.save(new CharacterEntity(berten, "Nilus"));
		characterRepository.save(new CharacterEntity(tim, "Bors"));
	}
}
