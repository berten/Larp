package org.deschutter.eternica.character;

import org.deschutter.eternica.init.DBConfig;
import org.deschutter.user.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.allOf;

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DBConfig.class})
public class CharacterDaoTest {
    @Autowired
    CharacterDao characterDao;
    @Test
    public void testFindByUserEntityId() throws Exception {
        CharacterEntity character = new CharacterEntity(new UserEntity("username","password"),"Character1");
        characterDao.save(character);
        Iterable<CharacterEntity> entities = characterDao.findAll();
        assertThat(entities,hasItem(allOf(hasProperty("characterName", is("CharacterName1")))));
    }
}
