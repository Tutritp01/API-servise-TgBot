package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;


@Component
public class InMemoryCustomerPersistence implements CustomerPersistence {
    public static final Logger logger = LoggerFactory.getLogger("CustomerPersistence logger");
    private final static HashSet<Customer> customers = new HashSet<>();


    @Override
    public Customer save(Customer customer) {
        if (!customers.contains(customer)) {
            customer.setId(UUID.randomUUID().toString());
            customers.add(customer);
        } else {
            logger.info("Customers already exists");
        }
        return customer;

    }

    @Override
    public Customer findById(String id) {
        for (final Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
