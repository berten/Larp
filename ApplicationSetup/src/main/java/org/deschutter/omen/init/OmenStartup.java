package org.deschutter.omen.init;

import org.deschutter.omen.character.CharacterDao;
import org.deschutter.omen.character.CharacterEntity;
import org.deschutter.omen.race.RaceDao;
import org.deschutter.omen.race.RaceEntity;
import org.deschutter.user.UserDao;
import org.deschutter.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class OmenStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
	private UserDao userDao;

	@Autowired
	private CharacterDao characterDao;

    @Autowired
    private RaceDao raceDao;


    @Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		characterDao.deleteAll();
		userDao.deleteAll();
        UserEntity berten = userDao.save(new UserEntity("Berten", "pwBerten"));
        UserEntity tim = userDao.save(new UserEntity("Tim", "pwTim"));
        RaceEntity race = raceDao.save(new RaceEntity("Mensch"));
        characterDao.save(new CharacterEntity(berten, "Nilus", race));
        characterDao.save(new CharacterEntity(tim, "Bors", race));
    }
}
