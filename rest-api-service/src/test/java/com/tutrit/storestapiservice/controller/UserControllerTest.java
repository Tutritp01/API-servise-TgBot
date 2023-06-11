package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.configurations.SpringContext;
import com.tutrit.storestapiservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserControllerTest {
    @Autowired
    UserController userController;
    @MockBean
    UserService userService;

    @Test
    void getById() {
        when(userService.findById("1")).thenReturn(expectedUser());
        User actualUser = userController.getById("1");
        assertEquals(expectedUser(), actualUser);
    }

    @Test
    void post() {
        when(userService.save(createNewUser())).thenReturn(expectedUser());
        User actualUser = userController.post(createNewUser());
        assertEquals(expectedUser(), actualUser);
    }

    @Test
    void findAll() {
        List<User> users = new ArrayList<>();
        users.add(expectedUser());
        users.add(expectedUser());
        when(userService.findAll()).thenReturn(users);
        List<User> actualUsers = userController.findAll();
        assertEquals(users, actualUsers);
    }

    @Test
    void update() throws AccessException {
        when(userService.save(updateUser())).thenReturn(expectedUser());
        User actualUser = userController.update(updateUser(), "1");
        assertEquals(expectedUser(), actualUser);
    }

    private User updateUser() {
        User updateUser = new User();
        updateUser.setUserId("1");
        updateUser.setName("Petr");
        updateUser.setPhoneNumber("111-123-1234");
        return updateUser;
    }

    @Test
    void delete() {
        when(userService.deleteById("1")).thenReturn(true);
        var isDeleted = userController.delete("1");
        assertTrue(isDeleted);
    }

    private User createNewUser() {
        var user = new User();
        user.setUserId("1");
        user.setName("Ignat");
        user.setPhoneNumber("+1");
        return user;
    }


    private User expectedUser() {
        var user = new User();
        user.setUserId("1");
        user.setName("Ignat");
        user.setPhoneNumber("+375121212121");
        return user;
    }
}
