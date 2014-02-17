package org.deschutter.eternica.character;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceImplTest {
	@InjectMocks
	private CharacterService characterService = new CharacterServiceImpl();

	@Mock
	private CharacterRepository characterRepository;

	@Test
	public void getCharactersForUserId() {
		Character character1 = new Character("CharacterName1");
		Character character2 = new Character("CharacterName2");
		when(characterRepository.findByUserId(123L)).thenReturn(Arrays.asList(character1, character2));
		assertThat(characterService.getCharactersForUserID(123L), hasItem(character1));
		assertThat(characterService.getCharactersForUserID(123L), hasItem(character2));
	}
}
