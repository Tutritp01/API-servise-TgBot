package com.tutrit.springdata.sevice;

import com.tutrit.springdata.dao.UserEntityManagerDao;
import com.tutrit.springdata.entity.User;

public class UserEntityManagerService implements UserService{
    private final UserEntityManagerDao userEntityManagerDao;

    public UserEntityManagerService(final UserEntityManagerDao userEntityManagerDao) {
        this.userEntityManagerDao = userEntityManagerDao;
    }

    @Override
    public User save(User user) {
        return userEntityManagerDao.save(user);
    }

    @Override
    public User findById(Long id) {
        return userEntityManagerDao.findById(id)
                .orElse(new User());
    }

    @Override
    public void deleteById(Long id) {
        userEntityManagerDao.deleteById(id);
    }

    @Override
    public void update(User user, Long id) {
        userEntityManagerDao.update(user,id);

    }
}
