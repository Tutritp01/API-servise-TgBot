package com.tutrit.storestapiservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import com.tutrit.storestapiservice.client.CustomerClient;
import com.tutrit.storestapiservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    CustomerService customerService;
    @SpyBean
    CustomerClient customerClient;

    @MockBean
    CustomerPersistence customerPersistence;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void getById() throws Exception {
        when(customerPersistence.findById("1")).thenReturn(expectedCustomer());

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        var actualCustomer = objectMapper.readValue(body, Customer.class);

        assertEquals(expectedCustomer(), actualCustomer);
        Mockito.verify(customerService).findById("1");
        Mockito.verify(customerClient).findById("1");
        Mockito.verify(customerPersistence).findById("1");
    }

    @Test
    void post() throws Exception {
        when(customerPersistence.save(changeCustomerBeforeSave())).thenReturn(expectedCustomer());

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNewCustomer())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        var actualCustomer = objectMapper.readValue(body, Customer.class);

        assertEquals(expectedCustomer(), actualCustomer);
        Mockito.verify(customerService, times(1)).save(any());
        Mockito.verify(customerClient, times(1)).save(any());
        Mockito.verify(customerPersistence, times(1)).save(any());
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
        customer.setName("Yanki");
        customer.setId("1");
        customer.setCity("minsk");
        customer.setEmail("VasilBrest@mail.by");
        customer.setPhoneNumber("+375334573385");
        return customer;
    }
}
