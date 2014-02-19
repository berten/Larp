package org.deschutter.user;

import org.deschutter.database.init.DBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void findByUsernameAndPassword() throws Exception {
        UserEntity expectedUser = userDao.save(new UserEntity("username", "password"));
        UserEntity result = userDao.findByUsernameAndPassword("username", "password");
        assertEquals(expectedUser, result);
    }

    @Test
    public void save() {
        UserEntity user = new UserEntity("username", "password");
        UserEntity savedUserEntity = userDao.save(user);
        assertNotNull(savedUserEntity.getId());
    }

}
