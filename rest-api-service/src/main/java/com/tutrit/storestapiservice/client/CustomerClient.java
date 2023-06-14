package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Customer;
import com.tutrit.persistence.core.persistence.CustomerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class represents a client for accessing customer data.
 * It provides methods to save and retrieve customer information.
 *
 * <p>The {@code CustomerClient} class is designed for extension
 * to provide additional functionality
 * or customization. If you want to extend this class, make sure
 * to follow these guidelines:
 * - Override methods as needed, providing necessary implementation.
 * - Document any additional behavior or considerations specific
 * to the subclass.</p>
 */
@Component
public class CustomerClient {
    @Autowired(required = false)
    private CustomerPersistence customerPersistence;

    /**
     * Saves the specified customer.
     *
     * <p>This method can be overridden by subclasses to provide
     * custom behavior for saving customers.
     * If you extend this class and override this method, make
     * sure to document any specific behavior
     * or considerations.</p>
     *
     * @param customer The customer to be saved.
     * @return The saved customer.
     */
    public Customer save(final Customer customer) {
        customer.setName("Yanki");
        customerPersistence.save(customer);
        return customer;
    }

    /**
     * Finds a customer by its ID.
     *
     * <p>This method can be overridden by subclasses to provide
     * custom behavior for finding customers
     * by ID. If you extend this class and override this method,
     * make sure to document any specific
     * behavior or considerations.</p>
     *
     * @param id The ID of the customer to find.
     * @return The found customer, or {@code null} if no customer
     * with the specified ID exists.
     */
    public Customer findById(final String id) {
        return customerPersistence.findById(id);
    }
}
