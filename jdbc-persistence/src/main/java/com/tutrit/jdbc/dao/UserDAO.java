package com.tutrit.jdbc.dao;

import java.util.Optional;

public interface UserDAO<User, Long> {

    void save(User user);

    Optional<User> getById(Long id);

    void update(User user);

    void deleteById(Long id);

}
