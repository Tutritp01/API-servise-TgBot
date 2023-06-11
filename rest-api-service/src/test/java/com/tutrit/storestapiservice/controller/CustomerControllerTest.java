package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.configurations.SpringContext;
import com.tutrit.storestapiservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringContext.SpringConfig.class)
class CustomerControllerTest {
    @Autowired
    CustomerController customerController;

    @MockBean
    CustomerService customerService;


    @Test
    void getById() {
        when(customerService.findById("1")).thenReturn(expectedCustomer());
        Customer actualCustomer = customerController.getById("1");
        assertEquals(expectedCustomer(), actualCustomer);
    }

    @Test
    void post() {
        when(customerService.save(createNewCustomer())).thenReturn(expectedCustomer());
        Customer actualCustomer = customerController.post(createNewCustomer());
        assertEquals(expectedCustomer(), actualCustomer);

    }

    private Customer createNewCustomer() {
        var customer = new Customer();
        customer.setName("Vasylyi");
        customer.setCustomerId("1");
        customer.setCity("Brest");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }


    private Customer expectedCustomer() {
        var customer = new Customer();
        customer.setName("Vasylyi");
        customer.setCustomerId("1");
        customer.setCity("minsk");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }
}
