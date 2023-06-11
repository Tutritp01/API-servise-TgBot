package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import com.tutrit.storestapiservice.configurations.SpringContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class CarClientTest {
    @Autowired
    private CarClient carClient;

    @MockBean
    private CarPersistence carPersistence;

    @Test
    void save() {
        var car = makeVictim();
        when(carPersistence.save(makeVictim())).thenReturn(makeExpected());
        var actualCar = carClient.save(makeVictim());
        assertEquals(makeExpected(), actualCar);
    }

    @Test
    void findById() {
        when(carPersistence.findById("2")).thenReturn(makeExpected());
        var actualCar = carClient.findById("2");
        assertEquals(makeExpected(), actualCar);
    }

    private Car makeVictim() {
        var car = new Car();
        car.setCarId("2");
        return car;
    }

    private Car makeExpected() {
        var car = new Car();
        car.setCarId("2");
        car.setOwner("Master");
        car.setVin("123456789");
        car.setPlateNumber("1111");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setGeneration("I");
        car.setModification("B");
        car.setEngine("Benzin");
        car.setYear(2010);
        return car;
    }
}
