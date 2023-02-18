package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.storestapiservice.service.EngineerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EngineerControllerTest {
    @Autowired
    EngineerController engineerController;
    @MockBean
    EngineerService engineerService;

    @Test
    void getById() {
        when(engineerService.findById("1")).thenReturn(makeExpected());
        var actualEngineer = engineerController.getById("1");
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void postSave() {
        when(engineerService.save(makeDebut())).thenReturn(makeExpected());
        var actualEngineer = engineerController.postSave(makeDebut());
        assertEquals(makeExpected(), actualEngineer);
    }

    private Engineer makeDebut() {
        var engineer = new Engineer();
        engineer.setId("1");
        return engineer;
    }

    private Engineer makeExpected() {
        var engineer = new Engineer();
        engineer.setId("2");
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
