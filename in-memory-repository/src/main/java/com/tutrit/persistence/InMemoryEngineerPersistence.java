package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class InMemoryEngineerPersistence implements EngineerPersistence {
    public static Engineer[] engineers = new Engineer[15];

    @Override
    public Engineer save(Engineer engineer) {
        int returnIndex = 0;
        for (int i = 0; i < engineers.length; i++) {
            if (engineers[i] == null) {
                engineer.setId(UUID.randomUUID().toString());
                returnIndex = i;
                engineers[i] = engineer;
                if ((i * 2) >= engineers.length) {
                    engineers = Arrays.copyOf(engineers, engineers.length + 5);
                }
                break;
            }
        }
        return engineers[returnIndex];
    }

    @Override
    public Engineer findById(String id) {
        for (Engineer engineerRepository : engineers) {
            if (engineerRepository != null && engineerRepository.getId().equals(id)) {
                return engineerRepository;
            }
        }
        return null;
    }
}
