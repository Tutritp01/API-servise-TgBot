package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.jdbc.config.ConnectionInterfaces;
import com.tutrit.persistence.jdbc.config.SpringContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserPersistenceJdbcTest {
    @Autowired
    @Qualifier("connectionEmbeddedProvider")
    private ConnectionInterfaces connectionInterfaces;

    @Autowired
    private UserPersistenceJdbc userPersistenceJdbc;

    public User createUser() {
        return new User("45c39ef0-2268-1111-aa93-a425be52eada", "Jimer Hendrix", "5554-12345");
    }

    private void createTable(ConnectionInterfaces connectionProvider) {
        String sql = """
                CREATE TABLE IF NOT EXISTS `user` (
                `user_id` VARCHAR(255) PRIMARY KEY, 
                `name` VARCHAR(255), 
                `phone_number` VARCHAR(255));""";

        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User insertUser(ConnectionInterfaces connectionProvider) {
        User user = new User();
        String sql = """
                INSERT INTO `user` (`user_id`, `name`, `phone_number`)
                VALUES ('45c39ef0-2268-1111-aa93-a425be52eada', 'Jimer Hendrix', '5554-12345')""";
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @BeforeEach
    void setUp() {
        createTable(connectionInterfaces);
        insertUser(connectionInterfaces);
    }

    @AfterEach
    void tearDown() {
        userPersistenceJdbc.deleteById("45c39ef0-2268-1111-aa93-a425be52eada");
    }

    @Test
    void save() {
        String uuid = UUID.randomUUID().toString();
        User user = new User(uuid, "Jimer Hendrix", "5554-12345");
        User saveUser = userPersistenceJdbc.save(user);
        assertEquals(user, saveUser);
    }

    @Test
    void findById() {
        User findUser = userPersistenceJdbc.findById("45c39ef0-2268-1111-aa93-a425be52eada");
        assertEquals(createUser(), findUser);
    }

    @Test
    void update() {
        User updateUser = new User();
        updateUser.setUserId("45c39ef0-2268-1111-aa93-a425be52eada");
        updateUser.setName("Freddy");
        updateUser.setPhoneNumber("111-111-1111");
        userPersistenceJdbc.update(updateUser, "45c39ef0-2268-1111-aa93-a425be52eada");
        User findUser = userPersistenceJdbc.findById("45c39ef0-2268-1111-aa93-a425be52eada");
        assertEquals(updateUser, findUser);
    }

    @Test
    void deleteById() {
        userPersistenceJdbc.deleteById("45c39ef0-2268-1111-aa93-a425be52eada");
        User findUser = userPersistenceJdbc.findById("45c39ef0-2268-1111-aa93-a425be52eada");
        assertEquals(new User(null, null, null), findUser);
    }
}
