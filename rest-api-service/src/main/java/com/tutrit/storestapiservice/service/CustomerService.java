package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.client.CustomerClient;
import org.springframework.stereotype.Service;

@Service

public class CustomerService {
    private final CustomerClient customerClient;

    public CustomerService(final CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public Customer save(Customer customer) {
        customer.setCity("minsk");

        return customerClient.save(customer);
    }

    public Customer findById(String id) {
        return customerClient.findById(id);
    }
}
