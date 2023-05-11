package com.tutrit.jdbc.dao;

import com.tutrit.jdbc.config.JdbcIT;
import com.tutrit.jdbc.config.SpringContext;
import com.tutrit.jdbc.entity.User;
import com.tutrit.jdbc.sevice.UserJdbcTemplateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserJdbcTemplateServiceIT extends JdbcIT {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserJdbcTemplateService userJdbcTemplateService;

    private static final User user = new User("Tomi Hendrix", "555-12345");
    private User expectedUser;

    @BeforeEach
    void setUp() {
        expectedUser = userJdbcTemplateService.save(user);
    }

    @AfterEach
    void done() {
        userJdbcTemplateService.deleteById(expectedUser.getUserId());
    }

    @Test
    void save() {
        assertEquals(user, expectedUser);
    }

    @Test
    void findById() {
        User updateUser = userJdbcTemplateService.findById(expectedUser.getUserId());

        assertEquals(expectedUser, updateUser);
    }

    @Test
    void update() {
        User updateUser = new User("Freddy Mercury", "123-5555");
        userJdbcTemplateService.update(updateUser, expectedUser.getUserId());

        User foundUser = userJdbcTemplateService.findById(expectedUser.getUserId());

        assertEquals(updateUser.getPhoneNumber(), foundUser.getPhoneNumber());
        assertEquals(updateUser.getName(), foundUser.getName());
    }

    @Test
    void deleteById() {
        userJdbcTemplateService.deleteById(user.getUserId());
        User byId = userJdbcTemplateService.findById(user.getUserId());

        assertEquals(byId, new User(null, null, null));
    }

}
