package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class CarClient {
    @Autowired(required = false)
    private CarPersistence carPersistence;

    public Car save(final Car engineer) {
        return carPersistence.save(engineer);
    }

    public Car findById(final String id) {
        return carPersistence.findById(id);
    }
}
