package com.tutrit.springdata.sevice;

import com.tutrit.springdata.config.EntityManagerIT;
import com.tutrit.springdata.dao.UserEntityManagerDao;
import com.tutrit.springdata.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:hibernate-it.properties")
class UserEntityManagerServiceIT  extends EntityManagerIT {

    @Autowired
    private SessionFactory sessionFactory;
    private UserEntityManagerService userEntityManagerService;
    @Autowired
    private UserEntityManagerDao userEntityManagerDao;
    private Session session;

    private static final User user = new User("Tomi Hendrix", "555-12345");
    private User savedUser;

    @BeforeEach
    void setUp(){
        userEntityManagerService = new UserEntityManagerService(userEntityManagerDao);
        session = sessionFactory.getCurrentSession();
        savedUser = userEntityManagerService.save(user);
    }

    @Test
    void save() {
        assertEquals(user, savedUser);
    }

    @Test
    void findById() {
        User byId = userEntityManagerService.findById(savedUser.getUserId());

        assertEquals(savedUser, byId);
    }

    @Test
    void deleteById() {
        userEntityManagerService.deleteById(savedUser.getUserId());

        User byId = userEntityManagerService.findById(savedUser.getUserId());

        assertEquals(byId, new User(null, null, null));
    }

    @Test
    void update() {
        User updatedUser = new User("Till Lindeman", "+6987541");

        userEntityManagerService.update(updatedUser, savedUser.getUserId());
        User byId = userEntityManagerService.findById(savedUser.getUserId());

        assertEquals(byId.getPhoneNumber(), updatedUser.getPhoneNumber());
        assertEquals(byId.getName(), updatedUser.getName());
    }
}