package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EngineerClientTest {
    @Autowired
    EngineerClient engineerClient;
    @MockBean
    EngineerPersistence engineerPersistence;

    @Test
    void save() {
        when(engineerPersistence.save(makeDebut())).thenReturn(makeExpected());
        var actualEngineer = engineerClient.save(makeDebut());
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void findById() {
        when(engineerPersistence.findById("2")).thenReturn(makeExpected());
        var actualEngineer = engineerClient.findById("2");
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
