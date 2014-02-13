package org.deschutter.eternica;

import static org.mockito.Mockito.mock;

import org.deschutter.eternica.character.CharacterRepository;
import org.deschutter.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Bean
	UserRepository userRepository() {
		return mock(UserRepository.class);
	}

	@Bean
	CharacterRepository characterRepository() {
		return mock(CharacterRepository.class);
	}

}
