package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryCarPersistenceTest {

    private CarPersistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new InMemoryCarPersistence();
    }

    @Disabled
    @Test
    void save() {
        var actual = persistence.save(newCar());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newCar());
        assertNotNull(actual.getCarId());
    }

    @Disabled
    @Test
    void findById() {

        Car car = persistence.save(newCar());
        var actual = persistence.findById(car.getCarId());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newCar());
        assertNotNull(actual.getCarId());
    }

    private Car newCar() {
        var car = new Car();
        car.setCarId("2");
        car.setOwner("Master");
        car.setVin("23456789");
        car.setPlateNumber("1111");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setGeneration("I");
        car.setModification("B");
        car.setEngine("gasoline");
        car.setYear(2010);
        return car;
    }
}
