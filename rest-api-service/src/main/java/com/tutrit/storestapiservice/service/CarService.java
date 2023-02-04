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
        return null;
    }

    public Car findById(String id) {
        return null;
    }
}
