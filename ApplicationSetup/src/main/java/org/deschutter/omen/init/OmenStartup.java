package org.deschutter.omen.init;

import org.deschutter.omen.character.CharacterDao;
import org.deschutter.omen.character.CharacterEntity;
import org.deschutter.omen.clazz.ClassDao;
import org.deschutter.omen.clazz.ClassEntity;
import org.deschutter.omen.lineage.LineageEntity;
import org.deschutter.omen.race.LineageDao;
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
    private LineageDao lineageDao;

    @Autowired
    private ClassDao classDao;


    @Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		characterDao.deleteAll();
        lineageDao.deleteAll();
        classDao.deleteAll();
        userDao.deleteAll();
        UserEntity berten = userDao.save(new UserEntity("Berten", "pwBerten"));
        UserEntity tim = userDao.save(new UserEntity("Tim", "pwTim"));
        LineageEntity race = lineageDao.save(new LineageEntity("Mannheimer"));
        ClassEntity warrior = classDao.save(new ClassEntity("Warrior"));
        characterDao.save(new CharacterEntity(berten, "Nilus", race, warrior));
        characterDao.save(new CharacterEntity(tim, "Bors", race, warrior));
    }
}
