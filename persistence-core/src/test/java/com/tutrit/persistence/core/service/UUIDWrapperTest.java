package com.tutrit.persistence.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class UUIDWrapperTest {
    UUIDWrapper uuidWrapper = new UUIDWrapper();

    @Test
    void getID() {
        String actualID = uuidWrapper.getID();
        Assertions.assertNotNull(actualID);
        Assertions.assertInstanceOf(String.class, actualID);
    }

    @Test
    void getIdNotEquals() {
        Assertions.assertNotEquals(uuidWrapper.getID(), uuidWrapper.getID());
    }
}
