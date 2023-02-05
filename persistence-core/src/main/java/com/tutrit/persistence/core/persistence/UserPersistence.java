package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.User;

public interface UserPersistence {
    User save(User user);
    User findById(String id);

}
