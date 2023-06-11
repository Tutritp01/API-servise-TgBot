package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.storestapiservice.configurations.SpringContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class UserClientTest {
    @Autowired
    UserClient userClient;
    @MockBean
    UserPersistence userPersistence;

    @Test
    void save() {
        when(userPersistence.save(createNewUser())).thenReturn(expectedUser());
        var actualUser = userClient.save(createNewUser());
        assertEquals(expectedUser(), actualUser);
    }

    @Test
    void findById() {
        when(userPersistence.findById("1")).thenReturn(expectedUser());
        var actualUser = userClient.findById("1");
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
