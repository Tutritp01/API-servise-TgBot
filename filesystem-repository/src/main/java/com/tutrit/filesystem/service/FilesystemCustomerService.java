package com.tutrit.filesystem.service;

import com.tutrit.filesystem.persistence.FilesystemCustomerPersistence;
import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class FilesystemCustomerService implements CustomerService {
    private final FilesystemCustomerPersistence inMemoryCustomerPersistence;

    public FilesystemCustomerService(FilesystemCustomerPersistence inMemoryCustomerPersistence) {
        this.inMemoryCustomerPersistence = inMemoryCustomerPersistence;
    }

    @Override
    public Customer saveCustomer(Customer customer) throws RuntimeException {
        return inMemoryCustomerPersistence.save(customer);
    }

    @Override
    public Customer getCustomer(String id) throws RuntimeException {
        return inMemoryCustomerPersistence.findById(id);
    }
}
