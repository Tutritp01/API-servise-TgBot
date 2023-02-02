package com.tutrit.persistence.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engineer {
    private String id;
    private String firstName;
    private String lastName;
    private String function;
    private String category;
    private String education;
    private int experience;
    private int generalExperience;

}
