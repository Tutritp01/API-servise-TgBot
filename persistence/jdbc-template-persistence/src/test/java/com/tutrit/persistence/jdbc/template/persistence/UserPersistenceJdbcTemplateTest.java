package com.tutrit.persistence.jdbc.template.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.jdbc.template.config.SpringContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserPersistenceJdbcTemplateTest {

    @Autowired
    private UserPersistenceJdbcTemplate userPersistenceJdbcTemplate;
    private User saveUser;
    private User user ;

    public void createUser() {
        String id = UUID.randomUUID().toString();
        user = new User(id, "Jimi Hendrix", "5554-12345");
    }

    @BeforeEach
    void setUp() {
        createUser();
        saveUser=userPersistenceJdbcTemplate.save(user);
    }

    @Test
    void save() {
        assertEquals(user, saveUser);
    }

    @Test
    void findById() {
        User findUser = userPersistenceJdbcTemplate.findById(user.getUserId());
        assertEquals(user, findUser);
    }

    @Test
    void deleteById() {
        userPersistenceJdbcTemplate.deleteById(user.getUserId());
        User findUser = userPersistenceJdbcTemplate.findById(user.getUserId());
        assertEquals(new User(null, null, null), findUser);
    }

    @Test
    void update() {
        user.setName("Freddy");
        user.setPhoneNumber("111-111-1111");
        userPersistenceJdbcTemplate.update(user, user.getUserId());
        User findUser = userPersistenceJdbcTemplate.findById(user.getUserId());
        assertEquals(user, findUser);
    }
}
