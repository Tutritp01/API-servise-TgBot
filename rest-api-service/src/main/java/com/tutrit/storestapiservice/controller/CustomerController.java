package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.service.CustomerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    @GetMapping("/customers/{id}")
    public Customer getById(final @PathVariable String id) {
        return customerService.findById(id);
    }

    /**
     * Saves a new customer.
     *
     * @param customer The customer to be saved.
     * @return The saved customer.
     */
    @PostMapping("/customers")
    public Customer post(final @RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
