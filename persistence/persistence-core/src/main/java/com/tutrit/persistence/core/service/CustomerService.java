package com.tutrit.persistence.core.service;

import com.tutrit.persistence.core.bean.Customer;

@Deprecated
public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomer(String id);
}
