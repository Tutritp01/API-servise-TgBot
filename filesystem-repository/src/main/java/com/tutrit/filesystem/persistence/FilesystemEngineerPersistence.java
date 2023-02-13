package com.tutrit.filesystem.persistence;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.springframework.stereotype.Component;

@Component
public class FilesystemEngineerPersistence implements EngineerPersistence {
    public static final Engineer[] engineers = new Engineer[15];

    @Override
    public Engineer save(Engineer engineer) {
        throw new NotImplementedException("Not Implemented");
    }

    @Override
    public Engineer findById(String id) {
        throw new NotImplementedException("Not Implemented");
    }
}
