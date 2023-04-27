package com.tutrit.jdbc.repository;

import com.tutrit.jdbc.config.SpringContext;
import com.tutrit.jdbc.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql("classpath:create-tables.sql")
@ActiveProfiles("IT")
@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserRepositoryIntegrationTest {

    @Autowired
    private DataSource dataSource;
    private UserRepository userRepository;
    private User expectedUser;
    public static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.33");
    private static final User user = new User("Jimi Hendrix", "555-12345");

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
        userRepository = new UserRepository(dataSource);
        expectedUser = userRepository.save(user);
    }

    @Test
    void testSave() {
        assertEquals(user,expectedUser );

    }

    @Test
    void testFindById() {

        User returnedUser = userRepository.findById(user.getUserId());
        assertEquals(user,returnedUser );
    }

    @Test
    void testUpdate() {
        User freddyMercury = new User(user.getUserId(), "Freddy Mercury", "123-5555");

        userRepository.update(freddyMercury);

        User returnedUser = userRepository.findById(user.getUserId());
        assertEquals(freddyMercury.getPhoneNumber(), returnedUser.getPhoneNumber());
    }

    @Test
    void testDeleteById() {
        assertTrue(userRepository.deleteById(user.getUserId()));
    }
}
