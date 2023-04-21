package com.tutrit.jdbc.repository;


import com.tutrit.jdbc.config.SpringContext;
import com.tutrit.jdbc.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserRepositoryTest {
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        userRepository = new UserRepository(dataSource);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
    }

    @AfterEach
    public void end() throws SQLException {
        verify(connection).close();
        verify(statement).close();
    }
    private User actualUser(){
        User user = new User();
        user.setUserId(1L);
        user.setName("Alice");
        user.setPhoneNumber("555-1234");
        return user;
    }

    @Test
    void save() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong(1)).thenReturn(66L);

        User savedUser = userRepository.save(actualUser());

        verify(statement).setString(1, "Alice");
        verify(statement).setString(2, "555-1234");
        verify(statement).executeUpdate();

        assertEquals(66L, savedUser.getUserId());
        assertNotNull(savedUser.getUserId());
    }

    @Test
    void findById() throws SQLException {
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        when(resultSet.getLong(1)).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("Alice");
        when(resultSet.getString("phone_number")).thenReturn("555-1234");

        User expectedUser = userRepository.findById(1L);

        verify(statement).setLong(1, 1L);
        verify(statement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getString("name");
        verify(resultSet).getString("phone_number");

        assertEquals(expectedUser, actualUser());
    }

    @Test
    void testUpdate() throws SQLException {
        userRepository.update(actualUser());

        verify(statement).setString(1, "Alice");
        verify(statement).setString(2, "555-1234");
        verify(statement).setLong(3, 1L);
        verify(statement).executeUpdate();

    }

    @Test
    void testDeleteById() throws SQLException {
        userRepository.deleteById(1L);

        verify(statement).setLong(1, 1L);
        verify(statement).executeUpdate();


    }
}
