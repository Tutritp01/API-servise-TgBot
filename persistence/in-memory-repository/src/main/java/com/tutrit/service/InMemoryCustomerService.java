package com.tutrit.service;

import com.tutrit.persistence.InMemoryCustomerPersistence;
import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public final class InMemoryCustomerService implements CustomerService {
    private final InMemoryCustomerPersistence inMemoryCustomerPersistence;

    public InMemoryCustomerService(
            final InMemoryCustomerPersistence inMemoryCustomerPersistence) {
        this.inMemoryCustomerPersistence = inMemoryCustomerPersistence;
    }

    @Override
    public Customer saveCustomer(final Customer customer) {
        return inMemoryCustomerPersistence.save(customer);
    }

    @Override
    public Customer getCustomer(final String id) {
        return inMemoryCustomerPersistence.findById(id);
    }
}
