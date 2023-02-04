package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InMemoryCarPersistence implements CarPersistence {
    public static final Logger logger = LoggerFactory.getLogger("CarPersistence logger");
    private static final List<Car> cars = new ArrayList<>();


    @Override
    public Car save(Car car) {
        car.setId(UUID.randomUUID().toString());
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
                if (id.equals(value.getId())) {
                    car = value;
                }
            }
        } catch (RuntimeException e) {
            logger.info("Car not found!");
        }
        return car;
    }

    public boolean isContains(String id) {
        Car car = null;
        for (Car value : cars) {
            if (id.equals(value.getId())) {
                car = value;
            }
        }
        return car != null;
    }
}
