package com.tutrit.service;

import com.tutrit.persistence.InMemoryUserPersistence;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.service.UserService;

public class InMemoryUserService implements UserService {
    private InMemoryUserPersistence inMemoryUserPersistence;

    public InMemoryUserService(InMemoryUserPersistence inMemoryUserPersistence) {
        this.inMemoryUserPersistence = inMemoryUserPersistence;
    }

    @Override
    public User saveUser(User user) {
        return inMemoryUserPersistence.save(user);
    }

    @Override
    public User getUser(String id) {
        return inMemoryUserPersistence.findById(id);
    }
}
