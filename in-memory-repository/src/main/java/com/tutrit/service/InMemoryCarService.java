package com.tutrit.service;

import com.tutrit.persistence.InMemoryCarPersistence;
import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.service.CarService;

public class InMemoryCarService implements CarService {
    private InMemoryCarPersistence inMemoryCarPersistence;

    public InMemoryCarService(InMemoryCarPersistence inMemoryCarPersistence) {
        this.inMemoryCarPersistence = inMemoryCarPersistence;
    }

    @Override
    public Car saveCar(Car car) {
        return inMemoryCarPersistence.save(car);
    }

    @Override
    public Car getCar(String id) {
        return inMemoryCarPersistence.findById(id);
    }
}
