package com.tutrit.jdbc.dao;

import com.tutrit.jdbc.config.JdbcIT;
import com.tutrit.jdbc.config.SpringContext;
import com.tutrit.jdbc.entity.User;
import com.tutrit.jdbc.service.UserJdbcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserJdbcServiceIT extends JdbcIT {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserJdbcService userJdbcService;
    private User expectedUser;

    private static final User user = new User("Jimi Hendrix", "555-12345");

    @BeforeEach
    void setUp() {
        expectedUser = userJdbcService.save(user);
    }

    @Test
    void testSave() {
        assertEquals(user, expectedUser);
    }

    @Test
    void testFindById() {
        User returnedUser = userJdbcService.findById(user.getUserId());

        assertEquals(user, returnedUser);
    }

    @Test
    void testUpdate() {
        User updateUser = new User("Freddy Mercury", "123-5555");

        userJdbcService.update(updateUser, expectedUser.getUserId());
        User returnedUser = userJdbcService.findById(user.getUserId());

        assertEquals(updateUser.getPhoneNumber(), returnedUser.getPhoneNumber());
    }

    @Test
    void testDeleteById() {
        userJdbcService.deleteById(user.getUserId());
        User byId = userJdbcService.findById(user.getUserId());

        assertEquals(byId, new User(null, null, null));
    }
}
