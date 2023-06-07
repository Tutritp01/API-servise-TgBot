package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserPersistenceJdbcTemplate implements UserPersistence {
    private final JdbcTemplate jdbcTemplate;

    public UserPersistenceJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        user.setUserId(UUID.randomUUID().toString());
        String sql = "INSERT INTO user (user_id,name, phone_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getName(), user.getPhoneNumber());
        return user;
    }


    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return new User(null, null, null);
        }
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(User user, String id) {
        String sql = "UPDATE user SET name = ?, phone_number = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getPhoneNumber(), id);
    }

}
