package com.tutrit.storestapiservice.controller;


import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.service.CustomerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public final class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    public Customer getById(final @PathVariable String id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public Customer post(final @RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
