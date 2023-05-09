package com.tutrit.springdata.service;

import com.tutrit.springdata.entity.User;

public interface UserService {
    User save(User user);
    User findById(Long id);

    void deleteById(Long id);
    void update(User user,Long id);



}
