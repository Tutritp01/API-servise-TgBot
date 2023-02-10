package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
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

    private User createNewUser() {
        var user = new User();
        user.setId("1");
        user.setName("Ignat");
        user.setPhoneNumber("+1");
        return user;
    }


    private User expectedUser() {
        var user = new User();
        user.setId("1");
        user.setName("Ignat");
        user.setPhoneNumber("+375121212121");
        return user;
    }
}
