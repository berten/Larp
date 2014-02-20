package org.deschutter.omen.race;

import org.deschutter.database.init.DBConfig;
import org.deschutter.omen.lineage.LineageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class LineageDaoTest {
    @Autowired
    private LineageDao lineageDao;

    @Test
    public void save_findOne() {
        LineageEntity entity = lineageDao.save(new LineageEntity("name"));
        LineageEntity retrieved = lineageDao.findOne(entity.getId());
        assertThat(retrieved, allOf(hasProperty("name", is(entity.getName())), hasProperty("id", is(entity.getId()))));
    }
}
