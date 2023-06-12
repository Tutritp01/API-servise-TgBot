package com.tutrit.storestapiservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.persistence.core.persistence.UserPersistence;
import com.tutrit.storestapiservice.client.UserClient;
import com.tutrit.storestapiservice.configurations.SpringContext;
import com.tutrit.storestapiservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    UserService userService;
    @SpyBean
    UserClient userClient;

    @MockBean
    UserPersistence userPersistence;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void getById() throws Exception {
        when(userPersistence.findById("1")).thenReturn(expectedUser());

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/users/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        var actualUser = objectMapper.readValue(body, User.class);

        assertEquals(expectedUser(), actualUser);
        verify(userService).findById("1");
        verify(userClient).findById("1");
        verify(userPersistence).findById("1");
    }

    @Test
    void post() throws Exception {
        User expectedUser = makeUserBeforeSave();
        when(userPersistence.save(any(User.class))).thenReturn(expectedUser);

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        User actualUser = objectMapper.readValue(body, User.class);

        assertEquals(expectedUser, actualUser);
        verify(userService, times(1)).save(any(User.class));
        verify(userClient, times(1)).save(any(User.class));
        verify(userPersistence, times(1)).save(any(User.class));
    }

    private User expectedUser() {
        var user = new User();
        user.setUserId("2");
        user.setName("Ignat");
        user.setPhoneNumber("+375121212121");
        return user;
    }

    private User makeUserBeforeSave() {
        var user = new User();
        user.setUserId("2");
        return user;
    }
}
