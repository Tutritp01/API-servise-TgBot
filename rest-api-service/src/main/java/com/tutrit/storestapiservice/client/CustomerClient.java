package com.tutrit.storestapiservice.client;


import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class CustomerClient {
    @Autowired(required = false)
    private CustomerPersistence customerPersistence;

    public Customer save(final Customer customer) {
        customer.setName("Yanki");
        customerPersistence.save(customer);
        return customer;
    }

    public Customer findById(final String id) {
        return customerPersistence.findById(id);

    }
}

