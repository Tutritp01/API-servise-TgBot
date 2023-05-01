package com.tutrit.hibernate.repository;

import com.tutrit.hibernate.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserRepositoryHibernateIT extends IntegrationTestsForHibernate{

    @Autowired
    private UserRepositoryHibernate userRepositoryHibernate;
    private User saveUser;

    private  User user = new User("Red Label","555-987");

    @BeforeEach
    void setUp(){
        saveUser = userRepositoryHibernate.save(user);
    }

    @Test
    void save() {
        assertEquals(user, saveUser);
    }

    @Test
    void findById() {
        User foundUser = userRepositoryHibernate.findById(saveUser.getUserId());
        assertEquals(user, foundUser);
    }

    @Test
    void update() {
        User updateUser = new User("Jack Daniels", "666-1234");
        userRepositoryHibernate.update(updateUser,saveUser.getUserId());

        User foundUser = userRepositoryHibernate.findById(saveUser.getUserId());
        assertEquals(foundUser.getName(),updateUser.getName());
        assertEquals(foundUser.getPhoneNumber(),updateUser.getPhoneNumber());

    }

    @Test
    void deleteById() {
        userRepositoryHibernate.deleteById(saveUser.getUserId());
        User foundUser = userRepositoryHibernate.findById(saveUser.getUserId());
        assertNull(foundUser);

    }
}