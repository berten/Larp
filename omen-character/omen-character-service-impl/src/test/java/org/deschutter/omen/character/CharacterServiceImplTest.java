package org.deschutter.omen.character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceImplTest {
    @InjectMocks
    private CharacterService characterService = new CharacterServiceImpl();

    @Mock
    private CharacterRepository characterRepository;

    @Test
    public void getCharactersForUserId() {
        Character character1 = new CharacterStub(1L, "CharacterName1", "Lineage1", "Class1","ReligionName","WealthName");
        Character character2 = new CharacterStub(2L, "CharacterName2", "Lineage1", "Class2","ReligionName","WealthName");
        when(characterRepository.findByUserId(123L)).thenReturn(Arrays.asList(character1, character2));
        assertThat(characterService.getCharactersForUserID(123L), hasItem(new CharacterDTO(1L,"CharacterName1", "Lineage1", "Class1", "ReligionName", "WealthName")));
        assertThat(characterService.getCharactersForUserID(123L), hasItem(new CharacterDTO(2L,"CharacterName2", "Lineage1", "Class2", "ReligionName", "WealthName")));
    }

    @Test
    public void getCharacter () {
        Character character = new CharacterStub(1L, "CharacterName1", "Lineage1","Class","ReligionName","WealthName");
        when(characterRepository.findById(1L)).thenReturn(character);
        assertThat(characterService.getCharacter(1L), allOf(isA(CharacterDTO.class),hasProperty("id", is(1L)),hasProperty("characterName",is("CharacterName1")),hasProperty("lineageName",is("Lineage1")),hasProperty("className",is("Class")),hasProperty("religionName",is("ReligionName"))));
    }

    private class CharacterStub extends Character {
        public CharacterStub(Long id, String characterName, String lineageName, String className, String religionName, String wealthName) {
            setId(id);
            setCharacterName(characterName);
            setLineageName(lineageName);
            setClassName(className);
            setReligionName(religionName);
            setWealthName(wealthName);
        }
    }
}
