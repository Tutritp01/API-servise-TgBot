package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.storestapiservice.client.CarClient;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarClient carClient;

    public CarService(final CarClient carClient) {
        this.carClient = carClient;
    }

    public Car save(Car car) {
        if (car.getId().equals("1")) {
            car.setId("2");
        }
        return carClient.save(car);
    }

    public Car findById(String id) {
        return carClient.findById(id);
    }
}
