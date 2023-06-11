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

    /**
     * Saves a customer.
     *
     * @param customer The customer to be saved.
     * @return The saved customer.
     */
    public Customer save(final Customer customer) {
        customer.setCity("minsk");
        customerClient.save(customer);
        return customer;
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    public Customer findById(final String id) {
        return customerClient.findById(id);
    }
}
