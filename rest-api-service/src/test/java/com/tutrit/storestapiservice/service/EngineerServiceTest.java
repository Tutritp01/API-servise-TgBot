package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.storestapiservice.client.EngineerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class EngineerServiceTest {
    @Autowired
    EngineerService engineerService;
    @MockBean
    EngineerClient engineerClient;

    @Test
    void save() {
        var engineer = makeDebut();
        when(engineerClient.save(makeEngineerBeforeSave())).thenReturn(makeExpected());
        var actualEngineer = engineerService.save(engineer);
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void findById() {
        when(engineerClient.findById("2")).thenReturn(makeExpected());
        var actualEngineer = engineerService.findById("2");
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void findByIdNull() {
        var actualEngineer = engineerService.findById("2");
        assertNull(actualEngineer);
    }

    private Engineer makeDebut() {
        var engineer = new Engineer();
        engineer.setId("1");
        return engineer;
    }

    private Engineer makeEngineerBeforeSave() {
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
