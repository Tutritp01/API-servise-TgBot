package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public final  class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getById(final @PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User post(final @RequestBody User user) {
        return userService.save(user);
    }
}

