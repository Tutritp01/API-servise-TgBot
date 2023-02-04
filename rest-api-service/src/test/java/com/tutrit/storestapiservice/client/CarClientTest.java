package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.service.CarPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarClientTest {

    @Mock
    private CarPersistence carPersistence;

    @InjectMocks
    private CarClient carClient;

    @Test
    @DisplayName("Should throw an exception when the carpersistence is null")
    void saveWhenCarPersistenceIsNullThenThrowException() {
        assertThrows(NullPointerException.class, () -> carClient.save(new Car()));
    }

    @Test
    @DisplayName("Should save the car when the carpersistence is not null")
    void saveWhenCarPersistenceIsNotNull() {
        Car car = new Car();
        when(carPersistence.save(car)).thenReturn(car);
        assertEquals(car, carClient.save(car));
    }
}