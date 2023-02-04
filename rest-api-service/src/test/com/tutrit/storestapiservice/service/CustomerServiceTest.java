package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.storestapiservice.client.CustomerClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerClient customerClient;

    @Test
    void save() {
        Customer customer = createNewCustomer();
        when(customerClient.save(changeCustomerBeforeSave())).thenReturn(expectedCustomer());
        Customer actualCustomer = customerService.save(customer);
        assertEquals(expectedCustomer(), actualCustomer);
    }

    @Test
    void findById() {
        when(customerClient.findById("1")).thenReturn(expectedCustomer());
        var actualCustomer = customerService.findById("1");
        assertEquals(expectedCustomer(), actualCustomer);
    }

    private Customer createNewCustomer() {
        var customer = new Customer();
        customer.setName("Vasylyi");
        customer.setId("1");
        customer.setCity("Brest");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }

    private Customer changeCustomerBeforeSave() {
        var customer = new Customer();
        customer.setCity("minsk");
        return customer;
    }

    private Customer expectedCustomer() {
        var customer = new Customer();
        customer.setName("Vasylyi");
        customer.setId("1");
        customer.setCity("minsk");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }
}
