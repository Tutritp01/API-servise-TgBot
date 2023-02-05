package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.Car;

public interface CarPersistence {
    Car save(Car car);
    Car findById(String id);
}
