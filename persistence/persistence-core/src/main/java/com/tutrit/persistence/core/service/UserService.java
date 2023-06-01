package com.tutrit.persistence.core.service;

import com.tutrit.persistence.core.bean.User;

@Deprecated
public interface UserService {
    User saveUser(User user);

    User getUser(String id);
}
