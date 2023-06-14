package com.tutrit.storestapiservice.controller;


import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.rmi.AccessException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/users/{id}")
    public User getById(final @PathVariable String id) {
        return userService.findById(id);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Saves a new user.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    @PostMapping("/users")
    public User post(final @RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Updates a user.
     *
     * @param user   The updated user.
     * @param userId The ID of the user to be updated.
     * @return The updated user.
     * @throws AccessException If the provided user ID is invalid.
     */
    @PutMapping
    public User update(
            final @RequestBody User user,
            final @RequestParam String userId) throws AccessException {
        if (!userId.equals(user.getUserId())) {
            throw new AccessException("Invalid userId");
        }
        return userService.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return true if the user was successfully deleted, false otherwise.
     */
    @DeleteMapping("/users/{userId}")
    public boolean delete(final @PathVariable String userId) {
        return userService.deleteById(userId);
    }
}
