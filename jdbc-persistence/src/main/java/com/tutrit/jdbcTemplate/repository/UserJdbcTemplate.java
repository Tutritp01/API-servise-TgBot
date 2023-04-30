package com.tutrit.jdbcTemplate.repository;


import com.tutrit.jdbcTemplate.entity.User;

public interface UserJdbcTemplate {

    User save(User user);

    boolean deleteById(Long id);

    boolean update(Long id,User user);

    User findById(Long id);
}
