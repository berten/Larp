package org.deschutter.eternica.character;

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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterRepositoryImplTest {
    @InjectMocks
    private CharacterRepository characterRepository = new CharacterRepositoryImpl();
    @Mock
    private CharacterDao characterDao;

    @Test
    public void testFindByUserId() throws Exception {
        UserEntity user = new UserEntity("user1", "pass1");
        CharacterEntity character1 = new CharacterEntity(user, "CharacterName1");
        character1.setId(1L);
        CharacterEntity character2 = new CharacterEntity(user, "CharacterName2");
        character2.setId(2L);
        when(characterDao.findByUserEntityId(123L)).thenReturn(Arrays.asList(character1, character2));
        List<Character> characters = characterRepository.findByUserId(123);
        assertThat(characters, hasItem(new Character(1L, "CharacterName1")));
        assertThat(characters, hasItem(new Character(2L, "CharacterName2")));
    }
}
