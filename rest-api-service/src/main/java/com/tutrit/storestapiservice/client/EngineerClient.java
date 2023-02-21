package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Engineer;
import com.tutrit.persistence.core.persistence.EngineerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngineerClient {
    @Autowired(required = false)
    private EngineerPersistence engineerPersistence;

    public final Engineer save(final Engineer engineer) {
        return engineerPersistence.save(engineer);
    }

    public final Engineer findById(final String id) {
        return engineerPersistence.findById(id).orElseGet(Engineer::new);
    }
}
