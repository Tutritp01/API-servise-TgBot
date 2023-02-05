package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.storestapiservice.service.CarService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{id}")
    public Car getById(@PathVariable String id) {
        return carService.findById(id);
    }

    @PostMapping("/cars")
    public Car post(@RequestBody Car car) {
        return carService.save(car);
    }
}
