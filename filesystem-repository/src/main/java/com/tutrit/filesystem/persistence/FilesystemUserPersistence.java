package com.tutrit.filesystem.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FilesystemUserPersistence implements UserPersistence {
    private final static Map<String, User> userMap = new HashMap<>();

    @Override
    public User save(User user) {
        throw new NotImplementedException("Not Implemented");
    }

    @Override
    public User findById(String id) {
        throw new NotImplementedException("Not Implemented");
    }
}
