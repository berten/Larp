package org.deschutter.omen.religion;

import org.deschutter.database.init.DBConfig;
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
public class ReligionDaoTest {

    @Autowired
    private ReligionDao classDao;

    @Test
    public void testFindByUserEntityId() throws Exception {
        ReligionEntity religion1 = classDao.save(new ReligionEntity("Religion1"));
        ReligionEntity religion2 = classDao.save(new ReligionEntity("Religion2"));
        Iterable<ReligionEntity> entities = classDao.findAll();
        assertThat(entities, hasItem(religion1));
        assertThat(entities, hasItem(religion2));
    }

    @Test
    public void findOne() {
        ReligionEntity religion = classDao.save(new ReligionEntity("Religion1"));
        ReligionEntity returnedEntity = classDao.findOne(religion.getId());
        assertThat(returnedEntity, is(religion));
    }
}
