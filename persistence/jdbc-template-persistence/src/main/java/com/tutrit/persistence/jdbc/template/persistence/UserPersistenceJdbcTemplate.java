package com.tutrit.persistence.jdbc.template.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Custom implementation of the UserPersistence interface
 * using JdbcTemplate for JDBC operations.
 * This class can be extended to customize the persistence behavior.
 */
@Component
public class UserPersistenceJdbcTemplate implements UserPersistence {
    private final JdbcTemplate jdbcTemplate;

    public UserPersistenceJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves a user to the database.
     * This method can be safely overridden in a subclass
     * to customize the saving behavior.
     *
     * @param user the user to save
     * @return the saved user
     */
    @Override
    public User save(final User user) {
        user.setUserId(UUID.randomUUID().toString());
        String sql = """
                INSERT INTO user (user_id,name, phone_number)
                VALUES (?, ?, ?)""";
        jdbcTemplate.update(
                sql, user.getUserId(), user.getName(), user.getPhoneNumber());
        return user;
    }

    /**
     * Retrieves a user from the database
     * based on the given ID.
     * This method can be safely overridden in a subclass
     * to customize the retrieval behavior.
     *
     * @param id the ID of the user to retrieve
     * @return the retrieved user or a default User object if not found
     */
    @Override
    public User findById(final String id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return new User(null, null, null);
        }
    }

    /**
     * Deletes a user from the database
     * based on the given ID.
     * This method can be safely overridden in a subclass to
     * customize the deletion behavior.
     *
     * @param id the ID of the user to delete
     */
    public void deleteById(final String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Updates a user in the database based on the given ID.
     * This method can be safely overridden in a subclass
     * to customize the update behavior.
     *
     * @param user the updated user object
     * @param id   the ID of the user to update
     */
    public void update(final User user,
                       final String id) {
        String sql = """
                UPDATE user SET name = ?, phone_number = ?
                WHERE user_id = ?""";
        jdbcTemplate.update(sql, user.getName(), user.getPhoneNumber(), id);
    }

}
