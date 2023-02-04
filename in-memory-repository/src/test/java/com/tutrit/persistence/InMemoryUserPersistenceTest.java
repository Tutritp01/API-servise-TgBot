package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryUserPersistenceTest {

    private UserPersistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new InMemoryUserPersistence();
    }

    @Test
    void save() {
        var actual = persistence.save(newUser());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newUser());
        assertNotNull(actual.getId());
    }

    @Test
    void findById() {

        User user = persistence.save(newUser());
        var actual = persistence.findById(user.getId());
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