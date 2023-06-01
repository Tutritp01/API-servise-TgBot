package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.client.CustomerClient;
import org.springframework.stereotype.Service;

@Service

public final class CustomerService {
    private final CustomerClient customerClient;

    public CustomerService(final CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public Customer save(final Customer customer) {
        customer.setCity("minsk");
        customerClient.save(customer);
        return customer;
    }

    public Customer findById(final String id) {
        return customerClient.findById(id);
    }
}
