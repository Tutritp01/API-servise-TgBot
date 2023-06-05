package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.jdbc.config.ConnectionProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserPersistenceJdbcTest {
    @Autowired
    private UserPersistenceJdbc userPersistenceJdbc;
    private User saveUser;
    private User user ;

    public User createUser() {
        String id = UUID.randomUUID().toString();
        user = new User(id, "Jimi Hendrix", "5554-12345");
        return user;
    }

    @BeforeEach
    void setUp() {
        createUser();
        saveUser = userPersistenceJdbc.save(user);
    }

    @Test
    void save() {
        assertEquals(user, saveUser);
    }

    @Test
    void findById() {
        User findUser = userPersistenceJdbc.findById(user.getUserId());
        assertEquals(user, findUser);
    }

    @Test
    void update() {
        user.setName("Freddy");
        user.setPhoneNumber("111-111-1111");
        userPersistenceJdbc.update(user, user.getUserId());
        User findUser = userPersistenceJdbc.findById(user.getUserId());
        assertEquals(user, findUser);
    }

    @Test
    void deleteById() {
        userPersistenceJdbc.deleteById(user.getUserId());
        User findUser = userPersistenceJdbc.findById(user.getUserId());
        assertEquals(new User(null, null, null), findUser);
    }
}