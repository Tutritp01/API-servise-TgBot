package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.storestapiservice.client.EngineerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EngineerServiceTest {
    @Autowired
    EngineerService engineerService;
    @MockBean
    EngineerClient engineerClient;

    @Test
    void save() {
        when(engineerClient.save(makeVictim(""))).thenReturn(makeExpected());
        var actualEngineer = engineerService.save(makeVictim(""));
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void update() {
        when(engineerClient.save(makeVictim("1"))).thenReturn(makeExpected());
        var actualEngineer = engineerService.save(makeVictim("1"));
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void findById() {
        when(engineerClient.findById("2")).thenReturn(makeExpected());
        var actualEngineer = engineerService.findById("2");
        assertEquals(makeExpected(), actualEngineer);
    }

    private Engineer makeVictim(String id) {
        var engineer = new Engineer();
        engineer.setId(id);
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
