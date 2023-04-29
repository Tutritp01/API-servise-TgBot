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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql("classpath:create-tables.sql")
@ActiveProfiles("IT")
@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserRepositoryJdbcTemplateIT {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private UserRepositoryJdbcTemplate userRepositoryJdbcTemplate;
    private User expectedUser;
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
        expectedUser = userRepositoryJdbcTemplate.save(user);
    }

    @Test
    void save() {
        assertEquals(user, expectedUser);
    }

    @Test
    void deleteById() {
        assertTrue(userRepositoryJdbcTemplate.deleteById(user.getUserId()));

    }

    @Test
    void update() {
        User freddyMercury = new User(user.getUserId(), "Freddy Mercury", "123-5555");
        userRepositoryJdbcTemplate.update(freddyMercury);

        Optional<User> found = userRepositoryJdbcTemplate.findById(freddyMercury.getUserId());
        assertTrue(found.isPresent());

        User returnedUser = found.get();
        assertEquals(freddyMercury, returnedUser);
    }

    @Test
    void findById() {
        Optional<User> found = userRepositoryJdbcTemplate.findById(user.getUserId());
        assertTrue(found.isPresent());

        User returnedUser = found.get();
        assertEquals(user, returnedUser);
    }
}