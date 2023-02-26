package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerClientTest {
    @Autowired
    CustomerClient customerClient;
    @MockBean
    CustomerPersistence customerPersistence;

    @Test
    void save() {
        when(customerPersistence.save(changeCustomerBeforeSave())).thenReturn(expectedCustomer());
        var actualCustomer = customerClient.save(createNewCustomer());
        assertEquals(expectedCustomer(), actualCustomer);
    }


    @Test
    void findById() {

        when(customerPersistence.findById("1")).thenReturn(expectedCustomer());
        var actualCustomer = customerClient.findById("1");
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

    private Customer changeCustomerBeforeSave() {
        var customer = new Customer();
        customer.setName("Yanki");
        return customer;
    }

    private Customer expectedCustomer() {
        var customer = new Customer();
        customer.setName("Yanki");
        customer.setCustomerId("1");
        customer.setCity("Brest");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }
}
