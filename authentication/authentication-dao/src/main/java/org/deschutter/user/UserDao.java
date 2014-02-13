package org.deschutter.user;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);
}
