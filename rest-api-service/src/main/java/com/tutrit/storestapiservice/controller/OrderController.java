package com.tutrit.storestapiservice.controller;

import com.tutrit.persistence.core.bean.Order;
import com.tutrit.storestapiservice.service.OrderService;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves all orders.
     *
     * @return A list of all orders.
     */
    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The order with the specified ID.
     * @throws InstanceNotFoundException If the order
     * with the specified ID is not found.
     */
    @GetMapping("/orders/{id}")
    public Order findAll(
            final @PathVariable String id) throws InstanceNotFoundException {
        return orderService.findById(id);
    }

    /**
     * Saves a new order.
     *
     * @param order The order to be saved.
     * @return The saved order.
     */
    @PostMapping("/orders")
    public Order saveNew(final @RequestBody Order order) {
        return orderService.save(order);
    }

    /**
     * Updates an existing order.
     *
     * @param id    The ID of the order to update.
     * @param order The updated order.
     * @return The updated order.
     * @throws AccessException If the provided
     * order ID does not match the URI path.
     */
    @PutMapping("/orders/{id}")
    public Order update(
            final @PathVariable String id,
            final @RequestBody Order order
    ) throws AccessException {
        if (!id.equals(order.getOrderId())) {
            throw new AccessException(
                    "Order URI path doesn't match order ID");
            // TODO : Add custom exception handling
        }
        return orderService.save(order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to delete.
     * @return true if the order was successfully
     * deleted, false otherwise.
     */
    @DeleteMapping("/orders/{id}")
    public boolean deleteById(final @PathVariable String id) {
        return orderService.delete(id);
    }
}
