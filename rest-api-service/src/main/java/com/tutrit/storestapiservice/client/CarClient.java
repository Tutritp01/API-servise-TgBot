package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.service.CarPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarClient {
    @Autowired(required = false)
    private CarPersistence carPersistence;

    public Car save(Car engineer) {
        return carPersistence.save(engineer);
    }

    public Car findByName(String id) {
        return carPersistence.findById(id);
    }
}
