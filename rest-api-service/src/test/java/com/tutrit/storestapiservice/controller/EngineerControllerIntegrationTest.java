package com.tutrit.storestapiservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import com.tutrit.storestapiservice.client.EngineerClient;
import com.tutrit.storestapiservice.service.EngineerService;
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

@SpringBootTest
@AutoConfigureMockMvc
class EngineerControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @SpyBean
    EngineerService engineerService;
    @SpyBean
    EngineerClient engineerClient;
    @MockBean
    EngineerPersistence engineerPersistence;

    @Test
    void getById() throws Exception {
        when(engineerPersistence.findById("1")).thenReturn(makeExpected());
        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/engineers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        var actualEngineer = objectMapper.readValue(body, Engineer.class);
        assertEquals(makeExpected(), actualEngineer);
        Mockito.verify(engineerService).findById("1");
        Mockito.verify(engineerClient).findById("1");
        Mockito.verify(engineerPersistence).findById("1");
    }

    @Test
    void postSave() throws Exception {
        when(engineerPersistence.save(makeDebut())).thenReturn(makeExpected());
        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/engineers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(makeDebut())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        var actualEngineer = objectMapper.readValue(body, Engineer.class);
        assertEquals(makeExpected(), actualEngineer);
        Mockito.verify(engineerService, times(1)).save(any());
        Mockito.verify(engineerClient, times(1)).save(any());
        Mockito.verify(engineerPersistence, times(1)).save(any());
    }

    private Engineer makeDebut() {
        var engineer = new Engineer();
        engineer.setId("1");
        return engineer;
    }

    private Engineer makeExpected() {
        var engineer = new Engineer();
        engineer.setId("1");
        engineer.setFirstName("Oleg");
        engineer.setLastName("Ivanov");
        engineer.setFunction("Master");
        engineer.setCategory("1");
        engineer.setEducation("Higher");
        engineer.setExperience(5);
        engineer.setGeneralExperience(10);
        return engineer;
    }
}
