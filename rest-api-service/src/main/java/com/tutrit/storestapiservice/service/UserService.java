package com.tutrit.storestapiservice.service;


import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.client.UserClient;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    private final UserClient userClient;

    public UserService(final UserClient userClient) {
        this.userClient = userClient;
    }

    public User save(User user) {
        user.setPhoneNumber("+375121212121");
        userClient.save(user);
        return user;
    }

    public User findById(String id) {
        return userClient.findById(id);
    }
}
