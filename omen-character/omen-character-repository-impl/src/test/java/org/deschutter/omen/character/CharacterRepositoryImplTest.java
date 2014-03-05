package org.deschutter.omen.character;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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

@RunWith(MockitoJUnitRunner.class)
public class CharacterRepositoryImplTest {
    private final LineageEntity lineageEntity = new LineageEntity("Lineage1");
    private final ClassEntity classEntity = new ClassEntity("Class1");
    private final ReligionEntity religionEntity = new ReligionEntity("ReligionName");
    private final WealthEntity wealthEntity = new WealthEntity("WealthName");
    private final UserEntity userEntity= new UserEntity("user1", "pass1");
    @InjectMocks
    private CharacterRepository characterRepository = new CharacterRepositoryImpl();
    @Mock
    private CharacterDao characterDao;
    @Mock
    private CharacterFactory characterFactory;

    @Test
    public void testFindByUserId() throws Exception {
        
        UserEntity user = userEntity;
        CharacterEntity character1 = new CharacterEntity(user, "CharacterName1", lineageEntity, classEntity, religionEntity, wealthEntity);
        character1.setId(1L);
        CharacterEntity character2 = new CharacterEntity(user, "CharacterName2", lineageEntity, classEntity, religionEntity, wealthEntity);
        character2.setId(2L);
        when(characterDao.findByUserEntityId(123L)).thenReturn(Arrays.asList(character1, character2));

        StubbedCharacter char1 = new StubbedCharacter(1L, "CharacterName1", "Lineage1", "Class1", "ReligionName", "WealthName");
        when(characterFactory.create(1L, "CharacterName1", "Lineage1", "Class1", "ReligionName", "WealthName")).thenReturn(char1);

        StubbedCharacter char2 = new StubbedCharacter(2L, "CharacterName2", "Lineage1", "Class1", "ReligionName", "WealthName");
        when(characterFactory.create(2L, "CharacterName2", "Lineage1", "Class1", "ReligionName", "WealthName")).thenReturn(char2);

        List<Character> characters = characterRepository.findByUserId(123);

        assertThat(characters, hasItem(char1));
        assertThat(characters, hasItem(char2));
    }

    @Test
    public void testFindById_returnsCorrectResult() {
        StubbedCharacter char1 = new StubbedCharacter(1L, "CharacterName1", "Lineage1", "Class1", "ReligionName", "WealthName");
        when(characterFactory.create(1L, "CharacterName1", "Lineage1", "Class1", "ReligionName", "WealthName")).thenReturn(char1);

        createCharacterAndMockDaoCall("CharacterName1", userEntity, lineageEntity, classEntity, religionEntity, wealthEntity);
        assertEquals(char1, characterRepository.findById(1L));
    }

    private void createCharacterAndMockDaoCall(String characterName, UserEntity user, LineageEntity lineageEntity, ClassEntity classEntity, ReligionEntity religionEntity, WealthEntity wealthEntity) {
        CharacterEntity character = new CharacterEntity(user, characterName, lineageEntity, classEntity, religionEntity, wealthEntity);
        character.setId(1L);
        when(characterDao.findOne(1L)).thenReturn(character);
    }

	private class StubbedCharacter implements Character {
        private final Long id;
        private final String characterName;
        private final String lineageName;
        private final String className;
        private final String religionName;
        private final String wealthName;

        public StubbedCharacter(Long id, String characterName, String lineageName, String className, String religionName,
				String wealthName) {
            this.id = id;
            this.characterName = characterName;
            this.lineageName = lineageName;
            this.className = className;
            this.religionName = religionName;
            this.wealthName = wealthName;
        }

        public Long getId() {
            return id;
        }

        public String getCharacterName() {
            return characterName;
        }

        public String getLineageName() {
            return lineageName;
        }

        public String getClassName() {
            return className;
        }

        public String getReligionName() {
            return religionName;
        }

        public String getWealthName() {
            return wealthName;
        }
    }
}
