package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.persistence.jdbc.config.ConnectionProvider;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


@Component
public class UserPersistenceJdbc implements UserPersistence {
    private final ConnectionProvider connectionProvider;

    public UserPersistenceJdbc(final ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user (name, phone_number,user_id) VALUES (?, ?, ?)";
        user.setUserId(UUID.randomUUID().toString());
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setString(3, user.getUserId());
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user to database: " + e.getMessage(), e);
        }
    }

    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setUserId(resultSet.getString("user_id"));
                return user;
            }
            return new User();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user from database: " + e.getMessage(), e);
        }
    }

    public void update(User user, String id) {
        String sql = "UPDATE user SET name = ?, phone_number = ? WHERE user_id = ?";

        try (Connection con = connectionProvider.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setString(3, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user to database: " + e.getMessage(), e);
        }
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
