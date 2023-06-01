package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Slf4j
@Component
public class InMemoryCustomerPersistence implements CustomerPersistence {
    private final static HashSet<Customer> customers = new HashSet<>();


    @Override
    public Customer save(Customer customer) {
        if (!customers.contains(customer)) {
            customer.setCustomerId(UUID.randomUUID().toString());
            customers.add(customer);
        } else {
            log.info("Customers already exists");
        }
        return customer;

    }

    @Override
    public Customer findById(String id) {
        for (final Customer customer : customers) {
            if (customer.getCustomerId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
