package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.storestapiservice.client.UserClient;
import com.tutrit.storestapiservice.configurations.SpringContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserClient userClient;

    @Test
    void save() {
        var user = createNewUser();
        when(userClient.save(createNewUser())).thenReturn(expectedUser());
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
        user.setUserId("1");
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
