package com.tutrit.service;

import com.tutrit.persistence.InMemoryEngineerPersistence;
import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.service.EngineerService;
import org.springframework.stereotype.Service;

@Service
public final class InMemoryEngineerService implements EngineerService {
    private final InMemoryEngineerPersistence inMemoryEngineerPersistence;

    public InMemoryEngineerService(
            final InMemoryEngineerPersistence inMemoryEngineerPersistence) {
        this.inMemoryEngineerPersistence = inMemoryEngineerPersistence;
    }

    @Override
    public Engineer saveEngineer(final Engineer engineer) {
        return inMemoryEngineerPersistence.save(engineer);
    }

    @Override
    public Engineer getEngineer(final String id) {
        return inMemoryEngineerPersistence.findById(id);
    }
}
