package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.client.UserClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(final UserClient userClient) {
        this.userClient = userClient;
    }

    /**
     * Saves a user.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    public User save(final User user) {
        user.setPhoneNumber("+375121212121");
        userClient.save(user);
        return user;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    public User findById(final String id) {
        return userClient.findById(id);
    }
}
