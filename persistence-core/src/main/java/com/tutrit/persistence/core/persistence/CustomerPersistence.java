package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.Customer;


public interface CustomerPersistence {
    Customer save(Customer customer);

    Customer findById(String id);
}
