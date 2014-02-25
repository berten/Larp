package org.deschutter.omen.clazz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import javax.transaction.Transactional;

import org.deschutter.database.init.DBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ClassDaoTest {
    @Autowired
    private ClassDao classDao;

    @Test
    public void testFindByUserEntityId() throws Exception {
        ClassEntity clazz1 = classDao.save(new ClassEntity("Class1"));
        ClassEntity clazz2 = classDao.save(new ClassEntity("Class2"));
        Iterable<ClassEntity> entities = classDao.findAll();
        assertThat(entities, hasItem(clazz1));
        assertThat(entities, hasItem(clazz2));
    }

    @Test
    public void findOne() {
        ClassEntity clazz = classDao.save(new ClassEntity("Class1"));
        ClassEntity returnedEntity = classDao.findOne(clazz.getId());
        assertThat(returnedEntity, is(clazz));
    }
}
