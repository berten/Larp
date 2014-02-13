package org.deschutter.authentication.user;


public interface UserRepository {
    public User findByUsernameAndPassword(String username, String password);
}
