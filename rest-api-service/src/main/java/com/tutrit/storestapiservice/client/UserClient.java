package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class UserClient {

    @Autowired(required = false)
    private UserPersistence userPersistence;

    public User save(final User user) {
        user.setName("Ignat");
        userPersistence.save(user);
        return user;
    }

    public User findById(final String id) {
        return userPersistence.findById(id);
    }
}
