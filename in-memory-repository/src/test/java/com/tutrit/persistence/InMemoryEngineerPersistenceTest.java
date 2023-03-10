package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryEngineerPersistenceTest {

    private EngineerPersistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new InMemoryEngineerPersistence();
    }

    @Disabled
    @Test
    void save() {
        var actual = persistence.save(newEngineer());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newEngineer());
        assertNotNull(actual.getEngineerId());
    }

    @Disabled
    @Test
    void findById() {

        Engineer engineer = persistence.save(newEngineer());
        var actual = persistence.findById(engineer.getEngineerId());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newEngineer());
        assertNotNull(actual.getEngineerId());
    }

    private Engineer newEngineer() {
        var engineer = new Engineer();
        engineer.setCategory("First");
        engineer.setEngineerId("1");
        engineer.setEducation("Higher");
        engineer.setExperience(3);
        engineer.setGeneralExperience(10);
        engineer.setFunction("Guarantee");
        engineer.setLastName("Romanov");
        engineer.setFirstName("Piotr");
        return engineer;
    }
}
