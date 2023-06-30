package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.client.UserClient;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userClient.save(user);
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

    /**
     * Retrieves all users.
     *
     * @return The list of all users.
     */
    public List<User> findAll() {
        return userClient.findAll();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return true if the user was successfully deleted, false otherwise.
     */
    public boolean deleteById(final String id) {
        return userClient.deleteById(id);
    }
}
