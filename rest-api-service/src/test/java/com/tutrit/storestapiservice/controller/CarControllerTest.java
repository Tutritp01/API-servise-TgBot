package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.storestapiservice.configurations.SpringContext;
import com.tutrit.storestapiservice.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class CarControllerTest {

    @Autowired
    CarController carController;

    @MockBean
    CarService carService;

    @Test
    void getById() {
        when(carService.findById("1")).thenReturn(makeExpected());
        Car actualCar = carController.getById("1");
        assertEquals(makeExpected(), actualCar);
    }

    @Test
    void post() {
        when(carService.save(makeVictim())).thenReturn(makeExpected());
        Car actualCar = carController.post(makeVictim());
        assertEquals(makeExpected(), actualCar);
    }

    private Car makeVictim() {
        var car = new Car();
        car.setCarId("1");
        return car;
    }

    private Car makeExpected() {
        var car = new Car();
        car.setCarId("1");
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
