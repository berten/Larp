package org.deschutter.omen;

import org.deschutter.authentication.user.UserRepository;
import org.deschutter.omen.character.CharacterDao;
import org.deschutter.omen.character.CharacterService;
import org.deschutter.omen.character.ClassDao;
import org.deschutter.omen.race.LineageDao;
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
    LineageDao raceDao() {
        return mock(LineageDao.class);
    }

    @Bean
    CharacterService characterService() {
        return mock(CharacterService.class);
    }
}
