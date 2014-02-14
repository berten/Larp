package org.deschutter.authentication.user;


public class User {

    private Long userId;

    // Use Factory to create an instance
    User(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
