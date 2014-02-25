package org.deschutter.omen.wealth;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import javax.transaction.Transactional;

import org.deschutter.database.init.DBConfig;
import org.junit.Assert;
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
public class WealthDaoTest {

    @Autowired
    private WealthDao classDao;

    @Test
    public void testFindByUserEntityId() throws Exception {
        WealthEntity wealth1 = classDao.save(new WealthEntity("Wealth1"));
        WealthEntity wealth2 = classDao.save(new WealthEntity("Wealth2"));
        Iterable<WealthEntity> entities = classDao.findAll();
        Assert.assertThat(entities, hasItem(wealth1));
        Assert.assertThat(entities, hasItem(wealth2));
    }

    @Test
    public void findOne() {
        WealthEntity wealth = classDao.save(new WealthEntity("Wealth1"));
        WealthEntity returnedEntity = classDao.findOne(wealth.getId());
        Assert.assertThat(returnedEntity, is(wealth));
    }
}
