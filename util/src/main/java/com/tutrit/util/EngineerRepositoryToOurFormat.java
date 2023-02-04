package com.tutrit.util;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.EngineerRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class EngineerRepositoryToOurFormat {

    private EngineerRepositoryToOurFormat() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToFormat(File directory) throws IOException, IllegalAccessException {
        Iterable<Engineer> engineers = ApplicationContext.get(EngineerRepository.class).findAll();
        writeAllRepository(directory, engineers);
    }

    private static void writeAllRepository(File directory, Iterable<Engineer> engineers) throws IOException, IllegalAccessException {
        directory.mkdirs();

        for (Engineer engineer : engineers) {
            File newFile = new File(directory, engineer.getId());

            if (newFile.exists()) {
                Files.delete(newFile.toPath());
            } else {
                newFile.createNewFile();
            }

            writeFieldsToFile(engineer, newFile);
        }
    }

    private static void writeFieldsToFile(Engineer engineer, File newFile) throws IOException, IllegalAccessException {
        Class<Engineer> engineerRepositoryClass = Engineer.class;
        String name = engineerRepositoryClass.getSimpleName();

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
            fileWriter.write(name);
            fileWriter.newLine();
        }
        Field[] fields = engineerRepositoryClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.canAccess(engineer)) {
                field.setAccessible(true);
            }
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
                fileWriter.write(field.getName() + "=" + field.get(engineer).toString());
                fileWriter.newLine();
            }
        }
    }
}
