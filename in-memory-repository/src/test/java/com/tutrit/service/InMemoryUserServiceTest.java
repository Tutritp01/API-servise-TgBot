package com.tutrit.service;

import com.tutrit.persistence.InMemoryUserPersistence;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryUserServiceTest {

    private UserService userService;
    private final InMemoryUserPersistence inMemoryUserPersistence = new InMemoryUserPersistence();


    @BeforeEach
    public void setUp() {
        userService = new InMemoryUserService(inMemoryUserPersistence);
    }

    @Disabled
    @Test
    void save() {
        var actual = userService.saveUser(newUser());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newUser());
        assertNotNull(actual.getUserId());
    }

    @Disabled
    @Test
    void findById() {

        User user = userService.saveUser(newUser());
        var actual = userService.getUser(user.getUserId());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newUser());
        assertNotNull(actual.getUserId());
    }

    private User newUser() {
        var user = new User();
        user.setName("Vasylyi");
        user.setUserId("1");
        user.setPhoneNumber("+375334573385");
        return user;
    }
}
