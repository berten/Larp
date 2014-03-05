package org.deschutter.omen;

import org.deschutter.authentication.user.UserRepository;
import org.deschutter.omen.character.CharacterDao;
import org.deschutter.omen.character.CharacterService;
import org.deschutter.omen.clazz.ClassDao;
import org.deschutter.omen.race.LineageDao;
import org.deschutter.omen.religion.ReligionDao;
import org.deschutter.omen.skill.SkillDao;
import org.deschutter.omen.skill.SkillService;
import org.deschutter.omen.wealth.WealthDao;
import org.deschutter.user.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    @Bean
    CharacterDao characterRepository() {
        return mock(CharacterDao.class);
    }

    @Bean
    UserDao userDao() {
        return mock(UserDao.class);
    }

    @Bean
    ClassDao classDao() {
        return mock(ClassDao.class);
    }

    @Bean
    ReligionDao religionDao() {
        return mock(ReligionDao.class);
    }

    @Bean
    LineageDao raceDao() {
        return mock(LineageDao.class);
    }

    @Bean
    WealthDao wealthDao() {
        return mock(WealthDao.class);
    }


    @Bean
    CharacterService characterService() {
        return mock(CharacterService.class);
    }

    @Bean
    SkillService skillService() {
        return mock(SkillService.class);
    }
    @Bean
    SkillDao skillDao() {
        return mock(SkillDao.class);
    }
}
