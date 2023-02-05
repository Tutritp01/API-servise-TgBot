package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User post(@RequestBody User user) {
        return userService.save(user);
    }
}

