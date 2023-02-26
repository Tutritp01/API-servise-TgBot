package com.tutrit.persistence.core.service;

import com.tutrit.persistence.core.bean.Car;

public interface CarService {
    Car saveCar(Car car);

    Car getCar(String id);
}
