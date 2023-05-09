package com.tutrit.springdata.service;

import com.tutrit.springdata.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserCrudServiceIT extends IntegrationTestForSpringData {
    @Autowired
    private UserCrudService userCrudService;
    private User saveUser = new User();
    private final User user = new User("Paul McCartney", "55-1234");

    @BeforeEach
    void setUp(){
        saveUser = userCrudService.save(user);
    }

    @Test
    void save() {
        assertEquals(saveUser, user);
    }

    @Test
    void findById() {
        User byId = userCrudService.findById(user.getUserId());
        assertEquals(byId, user);
    }

    @Test
    void deleteById() {
        userCrudService.deleteById(user.getUserId());
        User byId = userCrudService.findById(user.getUserId());
        assertEquals(byId,new User(null, null, null));
    }

    @Test
    void update() {
        User updateUser = new User("John Lennon", "123-555");
        userCrudService.update(updateUser, user.getUserId());
        User byId = userCrudService.findById(user.getUserId());
        assertEquals(updateUser.getName(),byId.getName());
        assertEquals(updateUser.getPhoneNumber(),byId.getPhoneNumber());

    }
}