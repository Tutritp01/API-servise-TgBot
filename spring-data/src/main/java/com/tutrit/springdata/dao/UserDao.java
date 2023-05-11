package com.tutrit.springdata.dao;

import com.tutrit.springdata.entity.User;

import java.util.Optional;

public interface UserDao {

    User save(User user);

    void deleteById(Long id);

    void update(User user, Long id);

    Optional<User> findById(Long id);
}
