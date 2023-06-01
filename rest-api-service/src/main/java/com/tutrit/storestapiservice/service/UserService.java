package com.tutrit.storestapiservice.service;


import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.client.UserClient;
import org.springframework.stereotype.Service;

@Service
public final  class UserService {

    private final UserClient userClient;

    public UserService(final UserClient userClient) {
        this.userClient = userClient;
    }

    public User save(final User user) {
        user.setPhoneNumber("+375121212121");
        userClient.save(user);
        return user;
    }

    public User findById(final String id) {
        return userClient.findById(id);
    }
}
