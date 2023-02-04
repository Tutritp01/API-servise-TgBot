package com.tutrit.util;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.stoservice.context.ApplicationContext;
import com.tutrit.stoservice.repository.CustomerRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class CustomerRepositoryToOurFormat {

    private CustomerRepositoryToOurFormat() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveToFormat(File directory) throws IOException, IllegalAccessException {
        Iterable<Customer> customers = ApplicationContext.get(CustomerRepository.class).findAll();
        writeAllRepository(directory, customers);
    }

    private static void writeAllRepository(File directory, Iterable<Customer> customers) throws IOException, IllegalAccessException {
        directory.mkdirs();

        for (Customer customer : customers) {
            File newFile = new File(directory, customer.getId());

            if (newFile.exists()) {
                Files.delete(newFile.toPath());
            } else {
                newFile.createNewFile();
            }

            writeFieldsToFile(customer, newFile);
        }
    }

    private static void writeFieldsToFile(Customer customer, File newFile) throws IOException, IllegalAccessException {
        Class<Customer> customerRepositoryClass = Customer.class;
        String name = customerRepositoryClass.getSimpleName();

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
            fileWriter.write(name);
            fileWriter.newLine();
        }
        Field[] fields = customerRepositoryClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.canAccess(customer)) {
                field.setAccessible(true);
            }
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(newFile, true))) {
                fileWriter.write(field.getName() + "=" + field.get(customer).toString());
                fileWriter.newLine();
            }
        }
    }
}
