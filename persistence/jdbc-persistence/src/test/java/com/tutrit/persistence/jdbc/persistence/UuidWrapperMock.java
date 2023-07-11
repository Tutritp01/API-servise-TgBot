package com.tutrit.persistence.jdbc.persistence;

import com.tutrit.persistence.jdbc.config.UuidWrapper;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UuidWrapperMock extends UuidWrapper {
    @Override
    public String randomUUID() {
        return UUID.fromString("45c39ef0-2268-0000-aa93-a425be52eada").toString();
    }
}
