package com.tutrit.filesystem.persistence;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class FilesystemCustomerPersistence implements CustomerPersistence {
    private final static HashSet<Customer> customers = new HashSet<>();

    @Override
    public Customer save(Customer customer) {
        throw new NotImplementedException("Not Implemented");
    }

    @Override
    public Customer findById(String id) {
        throw new NotImplementedException("Not Implemented");
    }
}
