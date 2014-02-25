package org.deschutter.omen.character;

import org.deschutter.database.init.DBConfig;
import org.deschutter.omen.clazz.ClassDao;
import org.deschutter.omen.clazz.ClassEntity;
import org.deschutter.omen.lineage.LineageEntity;
import org.deschutter.omen.race.LineageDao;
import org.deschutter.omen.religion.ReligionDao;
import org.deschutter.omen.religion.ReligionEntity;
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
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CharacterDaoTest {
    @Autowired
    CharacterDao characterDao;
    @Autowired
    UserDao userDao;
    @Autowired
    private LineageDao lineageDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private ReligionDao religionDao;

    @Test
    public void testFindByUserEntityId() throws Exception {
        UserEntity userEntity = userDao.save(new UserEntity("username", "password"));
        CharacterEntity character = characterDao.save(new CharacterEntity(userEntity, "Character1", lineageDao.save(new LineageEntity("Lineage1")), classDao.save(new ClassEntity("Class1")), religionDao.save(new ReligionEntity("ReligionName1"))));
        CharacterEntity character2 = characterDao.save(new CharacterEntity(userEntity, "Character2", lineageDao.save(new LineageEntity("Lineage1")), classDao.save(new ClassEntity("Class2")),religionDao.save(new ReligionEntity("ReligionName2"))));
        Iterable<CharacterEntity> entities = characterDao.findAll();
        assertThat(entities, hasItem(character));
        assertThat(entities, hasItem(character2));
    }

    @Test
    public void findOne() {
        CharacterEntity character = characterDao.save(new CharacterEntity(userDao.save(new UserEntity("username", "password")), "Character1", lineageDao.save(new LineageEntity("Lineage1")), classDao.save(new ClassEntity("Class1")), religionDao.save(new ReligionEntity("ReligionName"))));
        CharacterEntity returnedEntity = characterDao.findOne(character.getId());
        assertThat(returnedEntity, is(character));
    }
}
