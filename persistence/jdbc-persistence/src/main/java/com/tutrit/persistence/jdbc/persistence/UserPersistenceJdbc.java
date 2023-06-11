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
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;

    /**
     * Saves the user to the database. .
     *
     * @param user the user to be saved
     * @return the saved user
     */
    @Override
    public User save(final User user) {
        final String sql = """
                INSERT INTO user (user_id, name, phone_number)
                VALUES (?, ?, ?)""";
        user.setUserId(UUID.randomUUID().toString());
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(FIRST, user.getUserId());
            ps.setString(SECOND, user.getName());
            ps.setString(THIRD, user.getPhoneNumber());
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user to database: "
                    + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a user from the database based on the provided ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or an empty user if
     * not found
     * @throws RuntimeException if there is an error fetching the
     * user from the database
     */
    @Override
    public User findById(final String id) {
        final String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(FIRST, id);
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
            throw new RuntimeException("Error fetching user from database: "
                    + e.getMessage(), e);
        }
    }

    /**
     * Updates the user in the database with the provided information.
     *
     * @param user the updated user information
     * @param id   the ID of the user to update
     * @throws RuntimeException if there is an error updating the user
     * in the database
     */
    public void update(final User user, final String id) {
        final String sql = """
                UPDATE user SET name = ?, phone_number = ?
                WHERE user_id = ?""";

        try (Connection con = connectionProvider.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(FIRST, user.getName());
            ps.setString(SECOND, user.getPhoneNumber());
            ps.setString(THIRD, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user to database: "
                    + e.getMessage(), e);
        }
    }
    /**
     * Deletes a user from the database based on the specified ID.
     * @param id the ID of the user to delete
     * @throws RuntimeException if there is an error deleting
     * the user from the database
     */
    public void deleteById(final String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(FIRST, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error delete user to database: "
                    + e.getMessage(), e);
        }
    }

}
