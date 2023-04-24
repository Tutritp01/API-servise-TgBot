package com.tutrit.jdbc.service;

import com.tutrit.jdbc.entity.User;

public interface DAOService {

    User saveUser(User user);

    User findById(Long id);

    void updateUser(User user);

    void deleteById(Long id);
}
