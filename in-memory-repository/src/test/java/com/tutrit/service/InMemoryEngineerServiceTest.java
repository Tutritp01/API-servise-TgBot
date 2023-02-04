package com.tutrit.service;

import com.tutrit.persistence.InMemoryEngineerPersistence;
import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.service.EngineerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryEngineerServiceTest {
    private EngineerService engineerService;
    private final InMemoryEngineerPersistence inMemoryEngineerPersistence = new InMemoryEngineerPersistence();


    @BeforeEach
    public void setUp() {
        engineerService = new InMemoryEngineerService(inMemoryEngineerPersistence);
    }

    @Test
    void save() {
        var actual = engineerService.saveEngineer(newEngineer());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newEngineer());
        assertNotNull(actual.getId());
    }

    @Test
    void findById() {

        Engineer engineer = engineerService.saveEngineer(newEngineer());
        var actual = engineerService.getEngineer(engineer.getId());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newEngineer());
        assertNotNull(actual.getId());
    }

    private Engineer newEngineer() {
        var engineer = new Engineer();
        engineer.setCategory("First");
        engineer.setId("1");
        engineer.setEducation("Higher");
        engineer.setExperience(3);
        engineer.setGeneralExperience(10);
        engineer.setFunction("Guarantee");
        engineer.setLastName("Romanov");
        engineer.setFirstName("Piotr");
        return engineer;
    }
}
