package com.tutrit.jdbc.dao;

import com.tutrit.jdbc.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.util.Objects.requireNonNull;

@Component
public class UserJdbcTemplateDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateDao(final JdbcTemplate jdbcTemplate) {
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
    public void deleteById(Long id) {
        String query = """
                DELETE FROM users 
                WHERE user_id = ?
                """;
        jdbcTemplate.update(query, id);
    }

    @Override
    public void update(User user, Long id) {
        String query = """
                UPDATE users 
                SET name = ?, phone_number = ?  
                WHERE user_id=?
                """;
        jdbcTemplate.update(query, user.getName(), user.getPhoneNumber(), id);
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = """
                SELECT * 
                FROM users 
                WHERE user_id = ?
                """;
        List<User> users = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class), id);
        return users.stream().findAny();
    }

}
