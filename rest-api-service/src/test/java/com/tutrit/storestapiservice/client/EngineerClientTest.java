package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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
        when(engineerPersistence.save(makeVictim(""))).thenReturn(makeExpected());
        var actualEngineer = engineerClient.save(makeVictim(""));
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void update() {
        when(engineerPersistence.save(makeVictim("1"))).thenReturn(makeExpected());
        var actualEngineer = engineerClient.save(makeVictim("1"));
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void findById() {
        when(engineerPersistence.findById("2")).thenReturn(makeExpectedOptional());
        var actualEngineer = engineerClient.findById("2");
        assertEquals(makeExpected(), actualEngineer);
    }

    @Test
    void findByIdNotObjectEmpty() {
        when(engineerPersistence.findById("2")).thenReturn(makeExpectedOptionalEmpty());
        assertEquals(new Engineer(), engineerClient.findById("2"));
    }

    private Engineer makeVictim(String id) {
        var engineer = new Engineer();
        engineer.setId(id);
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

    private Optional<Engineer> makeExpectedOptional() {
        return Optional.of(makeExpected());
    }

    private Optional<Engineer> makeExpectedOptionalEmpty() {
        return Optional.empty();
    }
}
