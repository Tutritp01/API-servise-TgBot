package com.tutrit.jdbc.repository;

import com.tutrit.jdbc.config.SpringContext;
import com.tutrit.jdbc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;


@Sql(scripts = "classpath:/create-tables.sql")
@SpringBootTest(classes = SpringContext.SpringConfig.class)
@ActiveProfiles("test")
class UserRepositoryWithDockerContainerTest {
    @Autowired
    private DataSource dataSource;

    private UserRepository userRepository;

    private User expectedUser;
    private static final User user = new User("Jimi Hendrix", "555-12345");

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository(dataSource);
        expectedUser = userRepository.save(user);
    }

    @Test
    void testSave() {
        assertEquals(expectedUser, user);

    }

    @Test
    void testFindById() {

        User returnedUser = userRepository.findById(user.getUserId());
        assertEquals(returnedUser, user);
    }

    @Test
    void testUpdate() {
        User freddyMercury = new User(user.getUserId(),"Freddy Mercury", "123-5555");

        userRepository.update(freddyMercury);

        User returnedUser = userRepository.findById(user.getUserId());
        assertEquals(freddyMercury.getPhoneNumber(), returnedUser.getPhoneNumber());
    }

    @Test
    void testDeleteById() {
        assertTrue(userRepository.deleteById(user.getUserId()));
    }

}
