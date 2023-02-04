package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryCustomerPersistenceTest {

    private CustomerPersistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new InMemoryCustomerPersistence();
    }

    @Test
    void save() {
        var actual = persistence.save(newCustomer());
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newCustomer());
        assertNotNull(actual.getId());
    }

    @Test
    void findById() {

        Customer customer = persistence.save(newCustomer());
        var actual = persistence.findById(customer.getId());
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
