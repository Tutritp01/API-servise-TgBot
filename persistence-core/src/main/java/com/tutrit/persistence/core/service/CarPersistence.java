package com.tutrit.persistence.core.service;

import com.tutrit.persistence.core.bean.Car;

public interface CarPersistence {
    Car save(Car car);

    Car findById(String carId);
}
