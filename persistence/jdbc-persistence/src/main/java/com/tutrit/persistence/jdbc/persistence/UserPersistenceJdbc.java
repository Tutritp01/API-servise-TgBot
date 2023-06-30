package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.persistence.jdbc.config.ConnectionInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


@Component
public class UserPersistenceJdbc implements UserPersistence {
    @Autowired
    @Qualifier("connectionProvider")
    private ConnectionInterfaces connectionInterfaces;

    /**
     * Saves the user to the database. .
     *
     * @param user the user to be saved
     * @return the saved user
     */
    @Override
    public User save(final User user) {
        String sql = """
                INSERT INTO `user` (`user_id`, `name`, `phone_number`)
                VALUES (?, ?, ?)""";
        user.setUserId(UUID.randomUUID().toString());
        try (Connection connection = connectionInterfaces.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPhoneNumber());

            return ps.executeUpdate() == 1 ? user : new User();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user to database: "
                    + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a user from the database based on the provided ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or an empty user if not found
     * @throws RuntimeException if there is an error fetching the user from the
     *                          database
     */
    @Override
    public User findById(final String id) {
        String sql = "SELECT `user_id`, `name`, `phone_number` FROM `user` WHERE `user_id` = ?;";
        try (Connection connection = connectionInterfaces.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPhoneNumber(rs.getString(3));
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
     *                          in the database
     */
    public void update(final User user, final String id) {
        String sql = """
                UPDATE `user` SET `name` = ?, `phone_number` = ?
                WHERE `user_id` = ?""";
        try (Connection con = connectionInterfaces.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());
            ps.setString(3, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user to database: "
                    + e.getMessage(), e);
        }
    }

    /**
     * Deletes a user from the database based on the specified ID.
     *
     * @param id the ID of the user to delete
     * @throws RuntimeException if there is an error deleting
     *                          the user from the database
     */
    public void deleteById(final String id) {
        String sql = "DELETE FROM `user` WHERE `user_id` = ?";
        try (Connection connection = connectionInterfaces.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error delete user to database: "
                    + e.getMessage(), e);
        }
    }

}
