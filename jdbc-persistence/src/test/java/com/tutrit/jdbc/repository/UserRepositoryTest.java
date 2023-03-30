package com.tutrit.jdbc.repository;

import com.tutrit.persistence.core.bean.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class UserRepositoryTest {
    private static final User ACTUAL_USER = new User("1", "James Hetfield", "+1 123-456-7890");

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteById("1");
    }

    @Test
    void save() {
        userRepository.save(ACTUAL_USER);
        assertEquals(ACTUAL_USER, userRepository.findById("1"));
    }

    @Test
    void findById() {
        userRepository.save(ACTUAL_USER);
        assertEquals(ACTUAL_USER, userRepository.findById("1"));
    }

    @Test
    void update() {
        userRepository.save(ACTUAL_USER);
        userRepository.update(ACTUAL_USER);
        userRepository.findById("1");
        assertEquals(ACTUAL_USER, userRepository.findById("1"));
    }

    @Test
    void deleteById() {
        userRepository.save(ACTUAL_USER);
        userRepository.deleteById("1");
        assertFalse(userRepository.deleteById("1"));
    }
}
