package com.tutrit.sqldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String carId;
    private String owner;
    private String vin;
    private String plateNumber;
    private String brand;
    private String model;
    private String generation;
    private String modification;
    private String engine;
    private String engine2;
    private int madeYear;

}
