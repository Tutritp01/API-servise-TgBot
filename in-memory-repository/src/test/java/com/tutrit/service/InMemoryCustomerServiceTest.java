package com.tutrit.service;

import com.tutrit.persistence.InMemoryCustomerPersistence;
import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryCustomerServiceTest {
    private CustomerService customerService;
    private final InMemoryCustomerPersistence inMemoryCustomerPersistence = new InMemoryCustomerPersistence();


    @BeforeEach
    public void setUp() {
        customerService = new InMemoryCustomerService(inMemoryCustomerPersistence);
    }

    @Test
    void save() {
        var actual = customerService.saveCustomer(newCustomer());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newCustomer());
        assertNotNull(actual.getId());
    }

    @Test
    void findById() {

        Customer customer = customerService.saveCustomer(newCustomer());
        var actual = customerService.getCustomer(customer.getId());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newCustomer());
        assertNotNull(actual.getId());
    }

    private Customer newCustomer() {
        var customer = new Customer();
        customer.setName("Vasylyi");
        customer.setId("1");
        customer.setCity("Brest");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }
}
