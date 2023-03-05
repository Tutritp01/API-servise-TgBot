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
public class Engineer {
    @Id
    @GeneratedValue
    private Long id;
    private String engineerId;
    private String firstName;
    private String lastName;
    private String jobFunction;
    private String category;
    private String education;
    private int experience;
    private int generalExperience;

}
