package org.deschutter.eternica.character;

import org.deschutter.eternica.init.DBConfig;
import org.deschutter.user.UserDao;
import org.deschutter.user.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DBConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CharacterDaoTest {
    @Autowired
    CharacterDao characterDao;
    @Autowired
    UserDao userDao;

    @Test
    public void testFindByUserEntityId() throws Exception {
        UserEntity userEntity = userDao.save(new UserEntity("username", "password"));
        CharacterEntity character = characterDao.save(new CharacterEntity(userEntity, "Character1"));
        Iterable<CharacterEntity> entities = characterDao.findAll();
        assertThat(entities, hasItem(character));
    }
}
