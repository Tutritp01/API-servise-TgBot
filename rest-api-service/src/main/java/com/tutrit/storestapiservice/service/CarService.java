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

    /**
     * Saves a car.
     *
     * @param car The car to be saved.
     * @return The saved car.
     */
    public Car save(final Car car) {
        if (car.getCarId().equals("1")) {
            car.setCarId("2");
        }
        return carClient.save(car);
    }

    /**
     * Retrieves a car by ID.
     *
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     */
    public Car findById(final String id) {
        return carClient.findById(id);
    }
}
