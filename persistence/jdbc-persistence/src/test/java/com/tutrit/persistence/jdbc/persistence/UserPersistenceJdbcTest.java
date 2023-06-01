package com.tutrit.persistence.jdbc.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceJdbcTest {

    @Test
    void save() {
        new UserPersistenceJdbc().save(null);
    }

    @Test
    void findById() {
    }
}