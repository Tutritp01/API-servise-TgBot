package com.tutrit.persistence.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String id;
    private String owner;
    private String vin;
    private String plateNumber;
    private String brand;
    private String model;
    private String generation;
    private String modification;
    private String engine;
    private int year;

}
