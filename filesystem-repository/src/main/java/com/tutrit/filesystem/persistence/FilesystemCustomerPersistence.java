package com.tutrit.filesystem.persistence;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class FilesystemCustomerPersistence implements CustomerPersistence {
    private final static HashSet<Customer> customers = new HashSet<>();

    @Override
    public Customer save(Customer customer) throws RuntimeException {
        return null;
    }

    @Override
    public Customer findById(String id) throws RuntimeException {
        return null;
    }
}
