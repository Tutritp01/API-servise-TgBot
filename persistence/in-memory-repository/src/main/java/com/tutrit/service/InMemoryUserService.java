package com.tutrit.service;

import com.tutrit.persistence.InMemoryUserPersistence;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.service.UserService;
import org.springframework.stereotype.Service;

@Service
public final class InMemoryUserService implements UserService {
    private final InMemoryUserPersistence inMemoryUserPersistence;

    public InMemoryUserService(
            final InMemoryUserPersistence inMemoryUserPersistence) {
        this.inMemoryUserPersistence = inMemoryUserPersistence;
    }

    @Override
    public User saveUser(final User user) {
        return inMemoryUserPersistence.save(user);
    }

    @Override
    public User getUser(final String id) {
        return inMemoryUserPersistence.findById(id);
    }
}
