package com.tutrit.jdbc.service;


import com.tutrit.jdbc.dao.UserJdbcDao;
import com.tutrit.jdbc.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserJdbcService implements UserService {

    private final UserJdbcDao userJdbcDao;

    public UserJdbcService(final UserJdbcDao userJdbcDao) {
        this.userJdbcDao = userJdbcDao;
    }
    @Override
    public User save(User user) {
        return userJdbcDao.save(user);
    }
    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userJdbcDao.findById(id);
        return optionalUser.orElse(new User());
    }
    @Override
    public void update(User user) {
        userJdbcDao.update(user);
    }
    @Override
    public void deleteById(Long id) {
        userJdbcDao.deleteById(id);
    }

}
