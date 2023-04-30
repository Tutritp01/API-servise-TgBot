package com.tutrit.jdbcTemplate.repository;

import com.tutrit.jdbcTemplate.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.util.Objects.requireNonNull;

@Repository
public class UserRepositoryJdbcTemplate implements UserJdbcTemplate {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        String query = """
                INSERT INTO users ( name, phone_number) 
                VALUES (?, ?)
                """;
        var keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(connection -> {
            var statement = connection.prepareStatement(query, RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhoneNumber());
            return statement;
        }, keyHolder);

        user.setUserId(requireNonNull(keyHolder.getKey()).longValue());

        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = """
                DELETE FROM users 
                WHERE id = ?
                """;
        return jdbcTemplate.update(query, id) > 0;
    }

    @Override
    public boolean update(Long id, User user) {
        String query = """
                UPDATE users 
                SET name=?, phone_number=? 
                WHERE id=?
                """;
        return jdbcTemplate.update(query, user.getName(), user.getPhoneNumber(), id) > 0;
    }

    @Override
    public User findById(Long id) {
        String query = """
                SELECT * 
                FROM users 
                WHERE id = ?
                """;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

}
