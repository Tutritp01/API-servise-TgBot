package com.tutrit.jdbc.repository;

import com.tutrit.jdbc.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    private static final User ACTUAL_USER = new User(1L, "James Hetfield", "+1 123-456-7890");

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteById(1L);
    }

    @Test
    void save() {
        userRepository.save(ACTUAL_USER);
        Optional<User> savedUser = userRepository.getById(1L);
        assertTrue(savedUser.isPresent());
        assertEquals(ACTUAL_USER, savedUser.get());
    }

    @Test
    void getById() {
        userRepository.save(ACTUAL_USER);
        Optional<User> expectedUser = userRepository.getById(1L);
        assertTrue(expectedUser.isPresent());
        assertEquals(ACTUAL_USER, expectedUser.get());
    }

    @Test
    void update() {
        userRepository.save(ACTUAL_USER);
        userRepository.update(ACTUAL_USER);
        Optional<User> updatedUser = userRepository.getById(1L);
        assertTrue(updatedUser.isPresent());
        assertEquals(ACTUAL_USER, updatedUser.get());
    }

    @Test
    void deleteById() {
        userRepository.save(ACTUAL_USER);
        userRepository.deleteById(1L);
        Optional<User> deletedUser = userRepository.getById(1L);
        assertFalse(deletedUser.isPresent());
    }
}
