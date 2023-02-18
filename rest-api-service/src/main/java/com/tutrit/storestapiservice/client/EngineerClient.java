package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngineerClient {
    @Autowired(required = false)
    private EngineerPersistence engineerPersistence;

    public Engineer save(Engineer engineer) {
        return engineerPersistence.save(engineer);
    }

    public Engineer findById(String id) {
        try {
            return engineerPersistence.findById(id);
        } catch (RuntimeException e) {
            return new Engineer();
        }
    }
}
