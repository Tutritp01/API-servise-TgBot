package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.storestapiservice.service.CarService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(final CarService carService) {
        this.carService = carService;
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     */
    @GetMapping("/cars/{id}")
    public Car getById(final @PathVariable String id) {
        return carService.findById(id);
    }

    /**
     * Saves a new car.
     *
     * @param car The car to be saved.
     * @return The saved car.
     */
    @PostMapping("/cars")
    public Car post(final @RequestBody Car car) {
        return carService.save(car);
    }
}
