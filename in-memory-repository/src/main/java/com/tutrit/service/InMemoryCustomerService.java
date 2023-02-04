package com.tutrit.service;

import com.tutrit.persistence.InMemoryCustomerPersistence;
import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.service.CustomerService;

public class InMemoryCustomerService implements CustomerService {
    private InMemoryCustomerPersistence inMemoryCustomerPersistence;

    public InMemoryCustomerService(InMemoryCustomerPersistence inMemoryCustomerPersistence) {
        this.inMemoryCustomerPersistence = inMemoryCustomerPersistence;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return inMemoryCustomerPersistence.save(customer);
    }

    @Override
    public Customer getCustomer(String id) {
        return inMemoryCustomerPersistence.findById(id);
    }
}
