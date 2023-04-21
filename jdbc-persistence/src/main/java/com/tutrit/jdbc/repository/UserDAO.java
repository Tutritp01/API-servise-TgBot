package com.tutrit.jdbc.repository;

import com.tutrit.jdbc.entity.User;

public interface UserDAO {

    User save(User user);

    boolean deleteById(Long id);

    void update(User user);

    User findById(Long id);
}
