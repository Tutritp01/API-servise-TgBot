package com.tutrit.service;

import com.tutrit.persistence.InMemoryUserPersistence;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryUserServiceTest {

    private UserService userService;
    private final InMemoryUserPersistence inMemoryUserPersistence = new InMemoryUserPersistence();


    @BeforeEach
    public void setUp() {
        userService = new InMemoryUserService(inMemoryUserPersistence);
    }

    @Test
    void save() {
        var actual = userService.saveUser(newUser());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newUser());
        assertNotNull(actual.getId());
    }

    @Test
    void findById() {

        User user = userService.saveUser(newUser());
        var actual = userService.getUser(user.getId());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newUser());
        assertNotNull(actual.getId());
    }

    private User newUser() {
        var user = new User();
        user.setName("Vasylyi");
        user.setId("1");
        user.setPhoneNumber("+375334573385");
        return user;
    }
}
