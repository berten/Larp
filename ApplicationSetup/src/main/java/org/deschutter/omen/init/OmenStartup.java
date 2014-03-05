package org.deschutter.omen.init;

import org.deschutter.omen.character.CharacterDao;
import org.deschutter.omen.character.CharacterEntity;
import org.deschutter.omen.clazz.ClassDao;
import org.deschutter.omen.clazz.ClassEntity;
import org.deschutter.omen.lineage.LineageEntity;
import org.deschutter.omen.race.LineageDao;
import org.deschutter.omen.religion.ReligionDao;
import org.deschutter.omen.religion.ReligionEntity;
import org.deschutter.omen.skill.SkillDao;
import org.deschutter.omen.skill.SkillEntity;
import org.deschutter.omen.wealth.WealthDao;
import org.deschutter.omen.wealth.WealthEntity;
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
    @Autowired
    private ReligionDao religionDao;
    @Autowired
    private WealthDao wealthDao;
    @Autowired
    private SkillDao skillDao;


    @Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        clearAllTables();

        UserEntity berten = createUserEntity("Berten", "pwBerten");
        UserEntity tim = createUserEntity("Tim", "pwTim");

        LineageEntity race = lineageDao.save(new LineageEntity("Mannheimer"));

        ClassEntity warrior = classDao.save(new ClassEntity("strijder"));

        ReligionEntity religion = religionDao.save(new ReligionEntity("Hymir"));

        WealthEntity wealthEntity = wealthDao.save(new WealthEntity("Midden"));

        characterDao.save(new CharacterEntity(berten, "Nilus", race, warrior, religion, wealthEntity));
        characterDao.save(new CharacterEntity(tim, "Bors", race, warrior, religion, wealthEntity));

        skillDao.save(new SkillEntity("Adelstand","Je bent geboren in de adelstand."));
        skillDao.save(new SkillEntity("Afbakening 1","De Druïde kan een bepaald, beperkt stuk wildernis opeisen als zijn territorium. Men kan dit slechts 1x per\n" +
                "evenement initiëren: indien verdreven uit zijn territorium kan de Druïde dit niet meer doen tijdens een\n" +
                "evenement. Men ‘kiest’ een bepaald stuk wildernis uit het zicht van enige bebouwing en speelt uit dat men\n" +
                "een tiental grote vierkante meters afbakent (zegenen, stenen leggen, etc..)"));
    }

    private UserEntity createUserEntity(String userName, String passWord) {
        return userDao.save(new UserEntity(userName, passWord));
    }

    private void clearAllTables() {
        characterDao.deleteAll();
        lineageDao.deleteAll();
        classDao.deleteAll();
        userDao.deleteAll();
        religionDao.deleteAll();
        wealthDao.deleteAll();
        skillDao.deleteAll();
    }
}
