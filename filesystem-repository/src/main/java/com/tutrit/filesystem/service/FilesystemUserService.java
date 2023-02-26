package com.tutrit.filesystem.service;

import com.tutrit.filesystem.persistence.FilesystemUserPersistence;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class FilesystemUserService implements UserService {
    private final FilesystemUserPersistence inMemoryUserPersistence;

    public FilesystemUserService(FilesystemUserPersistence inMemoryUserPersistence) {
        this.inMemoryUserPersistence = inMemoryUserPersistence;
    }

    @Override
    public User saveUser(User user) throws RuntimeException {
        return inMemoryUserPersistence.save(user);
    }

    @Override
    public User getUser(String id) throws RuntimeException {
        return inMemoryUserPersistence.findById(id);
    }
}
