package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.storestapiservice.client.CarClient;
import org.springframework.stereotype.Service;

@Service
public final class CarService {

    private final CarClient carClient;

    public CarService(final CarClient carClient) {
        this.carClient = carClient;
    }

    public Car save(final Car car) {
        if (car.getCarId().equals("1")) {
            car.setCarId("2");
        }
        return carClient.save(car);
    }

    public Car findById(final String id) {
        return carClient.findById(id);
    }
}
