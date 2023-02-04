package com.tutrit.persistence.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private Customer customer;
    private User user;
    private Car car;
    private List<Engineer> engineers = new ArrayList<>();
    private String orderStatus;

}
