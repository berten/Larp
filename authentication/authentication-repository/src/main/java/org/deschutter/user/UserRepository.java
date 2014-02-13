package org.deschutter.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
}
