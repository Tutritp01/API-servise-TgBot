package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserClient {

    @Autowired(required = false)
    UserPersistence userPersistence;

    public User save(User user) {
        user.setName("Ignat");
        userPersistence.save(user);
        return user;
    }

    public User findById(String id) {
        return userPersistence.findById(id);
    }
}
