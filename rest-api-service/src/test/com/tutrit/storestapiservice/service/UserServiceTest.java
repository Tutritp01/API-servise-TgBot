package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.client.UserClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserClient userClient;

    @Test
    void save() {
        var user = createNewUser();
        when(userClient.save(changeUser())).thenReturn(expectedUser());
        User actualUser = userService.save(user);
        assertEquals(expectedUser(), actualUser);
    }

    @Test
    void findById() {
        when(userClient.findById("1")).thenReturn(expectedUser());
        var actualUser = userService.findById("1");
        assertEquals(expectedUser(), actualUser);
    }

    private User createNewUser() {
        var user = new User();
        user.setId("1");
        user.setName("Ignat");
        user.setPhoneNumber("+1");
        return user;
    }

    private User changeUser() {
        var user = new User();
        user.setName("Ignat");
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
