package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryUserPersistenceTest {

    private UserPersistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new InMemoryUserPersistence();
    }

    @Disabled
    @Test
    void save() {
        var actual = persistence.save(newUser());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newUser());
        assertNotNull(actual.getUserId());
    }

    @Disabled
    @Test
    void findById() {

        User user = persistence.save(newUser());
        var actual = persistence.findById(user.getUserId());
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
