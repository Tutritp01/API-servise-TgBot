package com.tutrit.springdata.dao;

import com.tutrit.springdata.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserEntityManagerDao implements UserDao {

    private final SessionFactory sessionFactory;
    private Session session;

    public UserEntityManagerDao(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public User save(User user) {
        session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Transactional
    public Optional<User> findById(Long id) {
        session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Transactional
    public void deleteById(Long id) {
        session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }

    @Transactional
    public void update(User user, Long id) {
        session = sessionFactory.getCurrentSession();
        User userUpdated = session.get(User.class, id);
        userUpdated.setName(user.getName());
        userUpdated.setPhoneNumber(user.getPhoneNumber());

    }

}
