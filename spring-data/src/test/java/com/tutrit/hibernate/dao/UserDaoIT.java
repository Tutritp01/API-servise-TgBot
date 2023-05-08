package com.tutrit.hibernate.dao;

import com.tutrit.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource("classpath:hibernate-it.properties")
@Transactional
class UserDaoIT extends IntegrationTestsForHibernate {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;

    private Session session;

    private static final User user = new User("Tomi Hendrix", "555-12345");

    private User savedUser;

    @Test
    void save() {
        session = sessionFactory.getCurrentSession();
        savedUser = userDao.save(user);
        assertEquals(savedUser, user);
    }

    @Test
    void findById() {
        session = sessionFactory.getCurrentSession();
        savedUser = userDao.save(user);
        User userDaoById = userDao.findById(savedUser.getUserId());
        assertEquals(savedUser, userDaoById);
    }

    @Test
    void deleteById() {
        session = sessionFactory.getCurrentSession();
        savedUser = userDao.save(user);
        userDao.deleteById(savedUser.getUserId());
        User byId = userDao.findById(savedUser.getUserId());
        assertNull(byId);
    }

    @Test
    void update() {
        User savedUser = userDao.save(user);

        User updatedUser = new User("Till Lindeman", "+6987541");
        userDao.update(updatedUser, savedUser.getUserId());
        User byId = userDao.findById(savedUser.getUserId());

        assertEquals(byId.getPhoneNumber(), updatedUser.getPhoneNumber());
        assertEquals(byId.getName(), updatedUser.getName());
    }
}