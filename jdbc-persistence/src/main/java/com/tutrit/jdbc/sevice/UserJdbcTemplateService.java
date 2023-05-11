package com.tutrit.jdbc.sevice;

import com.tutrit.jdbc.dao.UserJdbcTemplateDao;
import com.tutrit.jdbc.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserJdbcTemplateService implements UserService {

    private final UserJdbcTemplateDao userJdbcTemplateDao;

    public UserJdbcTemplateService(final UserJdbcTemplateDao userJdbcTemplateDao) {
        this.userJdbcTemplateDao = userJdbcTemplateDao;
    }

    @Override
    public User save(User user) {
        return userJdbcTemplateDao.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userJdbcTemplateDao.findById(id);
        return optionalUser.orElse(new User());
    }

    @Override
    public void update(User user, Long id) {
        userJdbcTemplateDao.update(user, id);

    }

    @Override
    public void deleteById(Long id) {
        userJdbcTemplateDao.deleteById(id);
    }
}
