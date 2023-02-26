package com.tutrit.filesystem.service;

import com.tutrit.filesystem.persistence.FilesystemEngineerPersistence;
import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.service.EngineerService;
import org.springframework.stereotype.Service;

@Service
public class FilesystemEngineerService implements EngineerService {
    private final FilesystemEngineerPersistence inMemoryEngineerPersistence;

    public FilesystemEngineerService(FilesystemEngineerPersistence inMemoryEngineerPersistence) {
        this.inMemoryEngineerPersistence = inMemoryEngineerPersistence;
    }

    @Override
    public Engineer saveEngineer(Engineer engineer) throws RuntimeException {
        return inMemoryEngineerPersistence.save(engineer);
    }

    @Override
    public Engineer getEngineer(String id) throws RuntimeException {
        return inMemoryEngineerPersistence.findById(id);
    }
}
