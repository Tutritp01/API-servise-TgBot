package com.tutrit.jdbc.dao;


import com.tutrit.jdbc.entity.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Component
public class UserJdbcDao implements UserDao {
    private final DataSource dataSource;

    public UserJdbcDao(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        String query = """
                INSERT INTO users ( name, phone_number) 
                VALUES (?, ?)
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setUserId(generatedKeys.getLong(1));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user to database: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = """
                SELECT name, phone_number 
                FROM users 
                WHERE user_id = ?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(id);
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phone_number"));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user to database: " + e.getMessage(), e);
        }
    }

    public void update(User user) {
        String query = """
                UPDATE users 
                SET name=?, phone_number=? 
                WHERE user_id=?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setLong(3, user.getUserId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user to database: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = """
                DELETE FROM users 
                WHERE user_id = ?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user to database: " + e.getMessage(), e);
        }
    }

}
