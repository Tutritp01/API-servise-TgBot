package com.tutrit.jdbc.dao;

import com.tutrit.jdbc.entity.User;

import java.util.Optional;

public interface UserDao {

    User save(User user);

    void deleteById(Long id);

    void update(User user);

    Optional<User> findById(Long id);
}
