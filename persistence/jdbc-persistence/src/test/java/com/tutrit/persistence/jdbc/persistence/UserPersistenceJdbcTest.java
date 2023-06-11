package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.persistence.jdbc.config.SpringContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserPersistenceJdbcTest {
    @Autowired
    private UserPersistenceJdbc userPersistenceJdbc;
    private User saveUser;
    private User user ;

    public void createUser() {
        String id = UUID.randomUUID().toString();
        user = new User(id, "Jimi Hendrix", "5554-12345");
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
