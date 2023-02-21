package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.Engineer;

import java.util.Optional;

public interface EngineerPersistence {
    Engineer save(Engineer engineer);

    Optional<Engineer> findById(String id);

    Iterable<Engineer> findAll();

    boolean delete(String id);
}
