package com.tutrit.service;

import com.tutrit.persistence.InMemoryEngineerPersistence;
import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.service.EngineerService;

public class InMemoryEngineerService implements EngineerService {
    private InMemoryEngineerPersistence inMemoryEngineerPersistence;

    public InMemoryEngineerService(InMemoryEngineerPersistence inMemoryEngineerPersistence) {
        this.inMemoryEngineerPersistence = inMemoryEngineerPersistence;
    }

    @Override
    public Engineer saveEngineer(Engineer engineer) {
        return inMemoryEngineerPersistence.save(engineer);
    }

    @Override
    public Engineer getEngineer(String id) {
        return inMemoryEngineerPersistence.findById(id);
    }
}
