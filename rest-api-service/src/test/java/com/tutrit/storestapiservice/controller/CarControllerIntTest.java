package com.tutrit.storestapiservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutrit.persistence.core.bean.Car;
import com.tutrit.persistence.core.persistence.CarPersistence;
import com.tutrit.storestapiservice.client.CarClient;
import com.tutrit.storestapiservice.configurations.SpringContext;
import com.tutrit.storestapiservice.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
@AutoConfigureMockMvc
class CarControllerIntTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @SpyBean
    CarService carService;
    @SpyBean
    CarClient carClient;
    @MockBean
    CarPersistence carPersistence;

    @Test
    void getById() throws Exception {
        when(carPersistence.findById("1")).thenReturn(makeExpected());

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/cars/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        var actualCar = objectMapper.readValue(body, Car.class);

        assertEquals(makeExpected(), actualCar);
        Mockito.verify(carService).findById("1");
        Mockito.verify(carClient).findById("1");
        Mockito.verify(carPersistence).findById("1");
    }

    @Test
    void post() throws Exception {
        when(carPersistence.save(makeCarBeforeSave())).thenReturn(makeExpected());

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(makeVictim())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        var actualEngineer = objectMapper.readValue(body, Car.class);

        assertEquals(makeExpected(), actualEngineer);
        Mockito.verify(carService, times(1)).save(any());
        Mockito.verify(carClient, times(1)).save(any());
        Mockito.verify(carPersistence, times(1)).save(any());
    }

    private Car makeVictim() {
        var car = new Car();
        car.setCarId("1");
        return car;
    }

    private Car makeExpected() {
        var car = new Car();
        car.setCarId("2");
        car.setOwner("Master");
        car.setVin("123456789");
        car.setPlateNumber("1111");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setGeneration("I");
        car.setModification("B");
        car.setEngine("Benzin");
        car.setYear(2010);
        return car;
    }

    private Car makeCarBeforeSave() {
        var car = new Car();
        car.setCarId("2");
        return car;
    }
}
