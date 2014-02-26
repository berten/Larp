package org.deschutter.omen.character;

import org.deschutter.omen.clazz.ClassEntity;
import org.deschutter.omen.lineage.LineageEntity;
import org.deschutter.omen.religion.ReligionEntity;
import org.deschutter.omen.wealth.WealthEntity;
import org.deschutter.user.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterRepositoryImplTest {
    private final LineageEntity lineageEntity = new LineageEntity("Lineage1");
    private final ClassEntity classEntity = new ClassEntity("Class1");
    private final ReligionEntity religionEntity = new ReligionEntity("ReligionName");
    private final WealthEntity wealthEntity = new WealthEntity("WealthName");
    @InjectMocks
    private CharacterRepository characterRepository = new CharacterRepositoryImpl();
    @Mock
    private CharacterDao characterDao;

    @Test
    public void testFindByUserId() throws Exception {
        UserEntity user = new UserEntity("user1", "pass1");
        CharacterEntity character1 = new CharacterEntity(user, "CharacterName1", lineageEntity, classEntity, religionEntity, wealthEntity);
        character1.setId(1L);
        CharacterEntity character2 = new CharacterEntity(user, "CharacterName2", lineageEntity, classEntity, religionEntity, wealthEntity);
        character2.setId(2L);
        when(characterDao.findByUserEntityId(123L)).thenReturn(Arrays.asList(character1, character2));
        List<org.deschutter.omen.character.Character> characters = characterRepository.findByUserId(123);
        assertThat(characters, hasItem(new Character(1L, "CharacterName1", "Lineage1", "Class", "ReligionName", "WealthName")));
        assertThat(characters, hasItem(new Character(2L, "CharacterName2", "Lineage1", "Class", "ReligionName", "WealthName")));
    }

    @Test
    public void testFindById_returnsCorrectResult() {
        createCharacterAndMockDaoCall();
        assertThat(characterRepository.findById(1L),allOf(hasProperty("id",is(1L)),hasProperty("characterName",is("CharacterName1"))));
    }

    @Test
    public void testFindById_hasRaceName() {
        createCharacterAndMockDaoCall();
        assertThat(characterRepository.findById(1L),allOf(hasProperty("id",is(1L)),hasProperty("lineageName",is("Lineage1"))));
    }

    @Test
    public void testFindById_hasClazzName() {
        createCharacterAndMockDaoCall();
        assertThat(characterRepository.findById(1L),allOf(hasProperty("id",is(1L)),hasProperty("className",is("Class1"))));
    }

    @Test
    public void testFindById_hasReligionName() {
        createCharacterAndMockDaoCall();
        assertThat(characterRepository.findById(1L),allOf(hasProperty("id",is(1L)),hasProperty("religionName",is("ReligionName"))));
    }

    @Test
    public void testFindById_hasWealthName() {
        createCharacterAndMockDaoCall();
        assertThat(characterRepository.findById(1L),allOf(hasProperty("id",is(1L)),hasProperty("wealthName",is("WealthName"))));
    }

    private void createCharacterAndMockDaoCall() {
        UserEntity user = new UserEntity("user1", "pass1");
        CharacterEntity character = new CharacterEntity(user, "CharacterName1", lineageEntity, classEntity, religionEntity, wealthEntity);
        character.setId(1L);
        when(characterDao.findOne(1L)).thenReturn(character);
    }
}
