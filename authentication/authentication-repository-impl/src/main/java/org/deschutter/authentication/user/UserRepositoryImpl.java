package org.deschutter.authentication.user;

import org.deschutter.user.UserDao;
import org.deschutter.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        UserEntity entity = userDao.findByUsernameAndPassword(username,password);
        return new User(entity.getId());
    }
}
