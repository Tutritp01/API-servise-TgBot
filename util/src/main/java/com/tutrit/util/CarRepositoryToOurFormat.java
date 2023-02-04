package com.tutrit.util;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.CarRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class CarRepositoryToOurFormat {

    private CarRepositoryToOurFormat() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToFormat(File directory) throws IOException, IllegalAccessException {
        Iterable<Car> cars = ApplicationContext.get(CarRepository.class).findAll();
        writeAllRepository(directory, cars);
    }

    private static void writeAllRepository(File directory, Iterable<Car> cars) throws IOException, IllegalAccessException {
        directory.mkdirs();

        for (Car car : cars) {
            File newFile = new File(directory, car.getId());

            if (newFile.exists()) {
                Files.delete(newFile.toPath());
            } else {
                newFile.createNewFile();
            }

            writeFieldsToFile(car, newFile);
        }
    }

    private static void writeFieldsToFile(Car car, File newFile) throws IOException, IllegalAccessException {
        Class<Car> engineerRepositoryClass = Car.class;
        String name = engineerRepositoryClass.getSimpleName();

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
            fileWriter.write(name);
            fileWriter.newLine();
        }
        Field[] fields = engineerRepositoryClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.canAccess(car)) {
                field.setAccessible(true);
            }
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
                fileWriter.write(field.getName() + "=" + field.get(car).toString());
                fileWriter.newLine();
            }
        }
    }
}
