package org.deschutter.eternica.init;

import org.deschutter.eternica.character.CharacterDao;
import org.deschutter.eternica.character.CharacterEntity;
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
	private CharacterDao characterDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		characterDao.deleteAll();
		userDao.deleteAll();
		UserEntity berten = new UserEntity("Berten", "pwBerten");
		userDao.save(berten);
		UserEntity tim = new UserEntity("Tim", "pwTim");
		userDao.save(tim);
		characterDao.save(new CharacterEntity(berten, "Nilus"));
		characterDao.save(new CharacterEntity(tim, "Bors"));
	}
}
