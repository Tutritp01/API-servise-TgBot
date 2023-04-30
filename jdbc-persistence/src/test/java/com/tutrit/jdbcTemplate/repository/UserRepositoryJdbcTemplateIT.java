package com.tutrit.jdbcTemplate.repository;

import com.tutrit.jdbcTemplate.config.SpringContext;
import com.tutrit.jdbcTemplate.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql("classpath:create-tables.sql")
@ActiveProfiles("IT")
@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserRepositoryJdbcTemplateIT {
    @Autowired
    private JdbcTemplate jdbcTemplate ;
    private UserRepositoryJdbcTemplate userRepositoryJdbcTemplate;

    public static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.33");
    private static final User user = new User("Tomi Hendrix", "555-12345");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @BeforeEach
    void setUp() {
        userRepositoryJdbcTemplate = new UserRepositoryJdbcTemplate(jdbcTemplate);

    }

    @Test
    void save() {
        User expectedUser = userRepositoryJdbcTemplate.save(user);
        assertEquals(user, expectedUser);
        userRepositoryJdbcTemplate.deleteById(expectedUser.getUserId());
    }

    @Test
    void deleteById() {
        User expectedUser = userRepositoryJdbcTemplate.save(user);
        assertTrue(userRepositoryJdbcTemplate.deleteById(user.getUserId()));
    }

    @Test
    void update() {
        User saveUser = userRepositoryJdbcTemplate.save(user);
        User updateUser = new User("Freddy Mercury", "123-5555");
        userRepositoryJdbcTemplate.update(saveUser.getUserId(),updateUser);

        User foundUser = userRepositoryJdbcTemplate.findById(saveUser.getUserId());

        assertEquals(updateUser.getPhoneNumber(), foundUser.getPhoneNumber());
        assertEquals(updateUser.getName(), foundUser.getName());
        userRepositoryJdbcTemplate.deleteById(saveUser.getUserId());

    }

    @Test
    void findById() {
        User expectedUser = userRepositoryJdbcTemplate.save(user);

        User userFoundById = userRepositoryJdbcTemplate.findById(expectedUser.getUserId());

        assertEquals(user.getName(), userFoundById.getName());
        assertEquals(user.getPhoneNumber(), userFoundById.getPhoneNumber());

        userRepositoryJdbcTemplate.deleteById(expectedUser.getUserId());

    }
}