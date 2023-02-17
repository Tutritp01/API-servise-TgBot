package com.tutrit.persistence.core.service;

import java.util.UUID;

public class UUIDWrapper {
    public String getID() {
        return UUID.randomUUID().toString();
    }
}
