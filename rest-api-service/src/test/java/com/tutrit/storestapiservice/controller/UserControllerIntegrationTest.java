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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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
            Mockito.verify(userService).findById("1");
            Mockito.verify(userClient).findById("1");
            Mockito.verify(userPersistence).findById("1");
        }

        @Test
        void post() throws Exception {
            when(userPersistence.save(changeUser())).thenReturn(expectedUser());

            final MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders.post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(createNewUser())))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            String body = result.getResponse().getContentAsString();
            var actualUser = objectMapper.readValue(body, User.class);

            assertEquals(expectedUser(), actualUser);
            Mockito.verify(userService, times(1)).save(any());
            Mockito.verify(userClient, times(1)).save(any());
            Mockito.verify(userPersistence, times(1)).save(any());
        }

    private User createNewUser() {
        var user = new User();
        user.setUserId("1");
        user.setName("Bob");
        user.setPhoneNumber("+375121212121");
        return user;
    }

    private User changeUser() {
        var user = new User();
        user.setName("Ignat");
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
