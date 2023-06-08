package com.tutrit.persistence.jdbc.persistence;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceJdbcTest {

    @Test
    @Disabled("that should be either integration test in other package, or integration with test db only for jdbc profile, or unit test")
    void save() {
        new UserPersistenceJdbc().save(null);
    }

    @Test
    void findById() {
    }
}