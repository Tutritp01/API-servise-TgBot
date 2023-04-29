package com.tutrit.jdbcTemplate.repository;


import com.tutrit.jdbcTemplate.entity.User;

import java.util.Optional;

public interface UserJdbcTemplate {

    User save(User user);

    boolean deleteById(Long id);

    boolean update(User user);

    Optional<User> findById(Long id);
}
