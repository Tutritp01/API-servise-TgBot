package com.tutrit.filesystem.persistence;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilesystemCarPersistence implements CarPersistence {
    private static final List<Car> cars = new ArrayList<>();

    @Override
    public Car save(Car car) {
        throw new NotImplementedException("Not Implemented");
    }

    @Override
    public Car findById(String id) {
        throw new NotImplementedException("Not Implemented");
    }
}
