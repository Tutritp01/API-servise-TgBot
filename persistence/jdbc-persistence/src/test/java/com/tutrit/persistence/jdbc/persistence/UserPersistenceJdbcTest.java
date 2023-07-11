package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.jdbc.config.ConnectionProvider;
import com.tutrit.persistence.jdbc.config.SpringContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserPersistenceJdbcTest {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `user` (`user_id` VARCHAR(255) PRIMARY KEY,`name` VARCHAR(255),`phone_number` VARCHAR(255))";
    @Autowired
    private ConnectionProvider connectionProvider;
    @Autowired
    private UuidWrapperMock uuidWrapperMock;
    private UserPersistenceJdbc userPersistenceJdbc;
    private User saved;


    private void createTable(ConnectionProvider connectionProvider) {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        userPersistenceJdbc = new UserPersistenceJdbc(connectionProvider, uuidWrapperMock);
        createTable(connectionProvider);
        saved = userPersistenceJdbc.save(nonPersistedUser());
    }

    @AfterEach
    void tearDown() {
        userPersistenceJdbc.deleteById(uuidWrapperMock.randomUUID());
    }

    private User expectedPersistedUser() {
        return new User("45c39ef0-2268-0000-aa93-a425be52eada", "Jimer Hendrix", "5554-12345");
    }

    private User nonPersistedUser() {
        return new User(null, "Jimer Hendrix", "5554-12345");
    }

    @Test
    void saveNewUser() {
        assertEquals(expectedPersistedUser(), saved);

    }

    @Test
    void findById() {
        User findUser = userPersistenceJdbc.findById(uuidWrapperMock.randomUUID());
        assertEquals(findUser, saved);
    }

    @Test
    void updateNewUser() {
        User expected = expectedPersistedUser();
        expected.setUserId(uuidWrapperMock.randomUUID());
        expected.setName("Roy Given");
        expected.setPhoneNumber("111-111-1111");
        User updated = userPersistenceJdbc.save(expected);
        assertEquals(expected, updated);
    }

    @Test
    void deleteById() {
        userPersistenceJdbc.deleteById(uuidWrapperMock.randomUUID());
        User findUser = userPersistenceJdbc.findById(uuidWrapperMock.randomUUID());
        assertEquals(new User(null, null, null), findUser);
    }

    @Test
    void findAll() {
        int size = userPersistenceJdbc.findAll().size();
        Assertions.assertEquals(1, size);
    }
}

