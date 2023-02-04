package com.tutrit.storestapiservice.controller;


import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    public Customer getById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public Customer post(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
