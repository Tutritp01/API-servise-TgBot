package com.tutrit.sqldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String orderId;
    @OneToOne
    private Customer customer;
    @OneToOne
    private User user;
    @OneToOne
    private Car car;
    @OneToMany
    private List<Engineer> engineers;
    private String orderStatus;

}
