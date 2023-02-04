package com.tutrit.filesystem.service;

import com.tutrit.filesystem.persistence.FilesystemCarPersistence;
import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class FilesystemCarService implements CarService {
    private final FilesystemCarPersistence filesystemCarPersistence;

    public FilesystemCarService(FilesystemCarPersistence filesystemCarPersistence) {
        this.filesystemCarPersistence = filesystemCarPersistence;
    }

    @Override
    public Car saveCar(Car car) throws RuntimeException {
        return filesystemCarPersistence.save(car);
    }

    @Override
    public Car getCar(String id) throws RuntimeException {
        return filesystemCarPersistence.findById(id);
    }
}
