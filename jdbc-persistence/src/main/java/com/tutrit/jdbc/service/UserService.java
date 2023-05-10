package com.tutrit.jdbc.service;

import com.tutrit.jdbc.entity.User;

public interface UserService {

    User save(User user);

    User findById(Long id);

    void update(User user);

    void deleteById(Long id);
}
