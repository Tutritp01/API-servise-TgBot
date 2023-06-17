package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.User;

import java.util.List;

public interface UserPersistence {
    User save(User user);
    User findById(String userId);
    List<User> findAll();
    boolean deleteById(String userId);

}
