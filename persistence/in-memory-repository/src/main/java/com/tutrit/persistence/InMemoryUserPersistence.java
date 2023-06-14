package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.exceptions.NotImplementedException;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class InMemoryUserPersistence implements UserPersistence {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public User save(final User user) {
        user.setUserId(UUID.randomUUID().toString());
        userMap.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User findById(final String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public boolean deleteById(String userId) {
        throw new NotImplementedException();
    }
}
