package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class InMemoryCarPersistence implements CarPersistence {
    private static final List<Car> cars = new ArrayList<>();


    @Override
    public Car save(Car car) {
        car.setCarId(UUID.randomUUID().toString());
        cars.add(car);
        return car;
    }

    @Override
    public Car findById(String id) {
        Car car = null;
        try {
            if (!isContains(id)) {
                throw new RuntimeException("Car not found!");
            }
            for (Car value : cars) {
                if (id.equals(value.getCarId())) {
                    car = value;
                }
            }
        } catch (RuntimeException e) {
            log.info("Car not found!");
        }
        return car;
    }

    public boolean isContains(String id) {
        Car car = null;
        for (Car value : cars) {
            if (id.equals(value.getCarId())) {
                car = value;
            }
        }
        return car != null;
    }
}
