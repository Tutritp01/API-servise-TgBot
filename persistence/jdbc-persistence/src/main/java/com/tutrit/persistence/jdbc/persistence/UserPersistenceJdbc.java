package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.exceptions.NotImplementedException;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.persistence.jdbc.config.ConnectionProvider;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

@Component
public class UserPersistenceJdbc implements UserPersistence {

    @Override
    public User save(User user) {
        Connection con = new ConnectionProvider().getConnection();
        String query = "";
        throw new NotImplementedException();
    }

    @Override
    public User findById(String id) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public boolean deleteById(String userId) {
        throw new NotImplementedException();
    }
}
