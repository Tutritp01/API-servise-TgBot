package com.tutrit.util;

import com.tutrit.persistence.core.bean.User;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.UserRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class UserRepositoryToOurFormat {

    private UserRepositoryToOurFormat() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToFormat(File directory) throws IOException, IllegalAccessException {
        Iterable<User> users = ApplicationContext.get(UserRepository.class).findAll();
        writeAllRepository(directory, users);
    }

    private static void writeAllRepository(File directory, Iterable<User> users) throws IOException, IllegalAccessException {
        directory.mkdirs();

        for (User user : users) {
            File newFile = new File(directory, user.getId());

            if (newFile.exists()) {
                Files.delete(newFile.toPath());
            } else {
                newFile.createNewFile();
            }

            writeFieldsToFile(user, newFile);
        }
    }

    private static void writeFieldsToFile(User user, File newFile) throws IOException, IllegalAccessException {
        Class<User> userRepositoryClass = User.class;
        String name = userRepositoryClass.getSimpleName();

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
            fileWriter.write(name);
            fileWriter.newLine();
        }
        Field[] fields = userRepositoryClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.canAccess(user)) {
                field.setAccessible(true);
            }
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
                fileWriter.write(field.getName() + "=" + field.get(user).toString());
                fileWriter.newLine();
            }
        }
    }
}
