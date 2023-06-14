package com.tutrit.storestapiservice.service;

import com.tutrit.persistence.core.bean.Order;
import com.tutrit.storestapiservice.client.OrderClient;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class OrderService {
    private final OrderClient orderClient;

    public OrderService(final OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    /**
     * Retrieves all orders.
     *
     * @return The list of all orders.
     */
    public List<Order> findAll() {
        return orderClient.findAll();
    }

    /**
     * Retrieves an order by ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The order with the specified ID.
     * @throws InstanceNotFoundException If the order
     * with the specified ID is not found.
     */
    public Order findById(final String id) throws InstanceNotFoundException {
        return orderClient.findById(id);
    }

    /**
     * Saves an order.
     *
     * @param order The order to be saved.
     * @return The saved order.
     */
    public Order save(final Order order) {
        return orderClient.save(order);
    }

    /**
     * Deletes an order by ID.
     *
     * @param orderId The ID of the order to delete.
     * @return true if the order is successfully
     * deleted, false otherwise.
     */
    public boolean delete(final String orderId) {
        return orderClient.delete(orderId);
    }
}
