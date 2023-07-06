package com.tutrit.persistence.jdbc.persistence;

import java.util.UUID;

public class UuidWrapperMock extends UuidWrapper{

    @Override
    public UUID randomUUID() {
        return UUID.fromString("45c39ef0-2268-0000-aa93-a425be52eada");
    }
}
