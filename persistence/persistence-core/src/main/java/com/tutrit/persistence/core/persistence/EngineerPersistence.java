package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.Engineer;

public interface EngineerPersistence {
    Engineer save(Engineer engineer);

    Engineer findById(String id);
}
