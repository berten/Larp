package org.deschutter.user;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
}
