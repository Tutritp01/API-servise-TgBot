package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public final class InMemoryEngineerPersistence implements EngineerPersistence {
    private static final int MAX_ENGINEERS = 15;
    private static final int ADDITIONAL_ENGINEERS = 5;
    private static Engineer[] engineers = new Engineer[MAX_ENGINEERS];


    @Override
    public Engineer save(final Engineer engineer) {
        int returnIndex = 0;
        for (int i = 0; i < engineers.length; i++) {
            if (engineers[i] == null) {
                engineer.setEngineerId(UUID.randomUUID().toString());
                returnIndex = i;
                engineers[i] = engineer;
                if ((i * 2) >= engineers.length) {
                    engineers = Arrays.copyOf(engineers,
                            engineers.length + ADDITIONAL_ENGINEERS);
                }
                break;
            }
        }
        return engineers[returnIndex];
    }

    @Override
    public Engineer findById(final String id) {
        for (Engineer engineerRepository : engineers) {
            if (engineerRepository != null
                    && engineerRepository.getEngineerId().equals(id)) {
                return engineerRepository;
            }
        }
        return null;
    }
}
