package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class represents a client
 * for accessing car data.
 * It provides methods to save and
 * retrieve car information.
 *
 * <p>The {@code CarClient} class is designed
 * for extension to provide additional
 * functionality or customization.
 * If you want to extend this class, make sure
 * to follow these guidelines:
 * - Override methods as needed, providing
 * necessary implementation.
 * - Document any additional behavior or
 * considerations specific to the subclass.</p>
 */
@Component
public class CarClient {
    @Autowired(required = false)
    private CarPersistence carPersistence;

    /**
     * Saves the specified car.
     *
     * @param car The car to be saved.
     * @return The saved car.
     */
    public Car save(final Car car) {
        return carPersistence.save(car);
    }

    /**
     * Finds a car by its ID.
     *
     * <p>This method can be overridden by subclasses
     * to provide custom behavior for finding cars by ID.
     * If you extend this class and override this method,
     * make sure to document any specific behavior or considerations.</p>
     *
     * @param id The ID of the car to find.
     * @return The found car, or {@code null} if no car
     * with the specified ID exists.
     */
    public Car findById(final String id) {
        return carPersistence.findById(id);
    }
}
