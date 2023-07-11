package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.persistence.jdbc.config.ConnectionProvider;
import com.tutrit.persistence.jdbc.config.UuidWrapper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


@Component
public class UserPersistenceJdbc implements UserPersistence {
    public static final String CREATE_USER = "INSERT INTO `user` (`user_id`, `name`, `phone_number`) VALUES (?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE `user` SET `name` = ?, `phone_number` = ?  WHERE `user_id` = ?";
    public static final String FIND_BY_ID = "SELECT `user_id`, `name`, `phone_number` FROM `user` WHERE `user_id` = ?;";
    public static final String DELETE_USER = "DELETE FROM `user` WHERE `user_id` = ?";
    public static final String FIND_ALL = "SELECT `user_id`, `name`, `phone_number` FROM `user`";

    private final ConnectionProvider connectionProvider;
    private final UuidWrapper uuidWrapper;

    public UserPersistenceJdbc(final ConnectionProvider connectionProvider, final UuidWrapper uuidWrapper) {
        this.connectionProvider = connectionProvider;
        this.uuidWrapper = uuidWrapper;
    }


    /**
     * Saves the user to the database. .
     *
     * @param user the user to be saved
     * @return the saved user
     */
    @Override
    public User save(final User user) {
        String sql;
        if (user.getUserId() == null) {
            sql = CREATE_USER;
            user.setUserId(uuidWrapper.randomUUID());
        } else {
            sql = UPDATE_USER;
        }
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPhoneNumber());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                user.setUserId(resultSet.getString(1));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user to database: " + e.getMessage(), e);
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
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapperUser(rs);
            }
            return new User();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user from database: " + e.getMessage(), e);
        }
    }

    private User mapperUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString(1));
        user.setName(rs.getString(2));
        user.setPhoneNumber(rs.getString(3));
        return user;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return all users
     * @throws RuntimeException if there is an error fetching the users from the database
     */

    @Override
    public List<User> findAll() {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = mapperUser(rs);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Deletes a user from the database based on the specified ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted
     * @throws RuntimeException if there is an error deleting
     *                          the user from the database
     */
    @Override
    public boolean deleteById(final String id) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        ) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error delete user to database: " + e.getMessage(), e);
        }
    }

}
