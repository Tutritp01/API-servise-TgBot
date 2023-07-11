package com.tutrit.persistence.jdbc.config;

import java.util.UUID;

public class UuidWrapper {
    public String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
