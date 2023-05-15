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
public final  class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order findAll(final @PathVariable String id)
            throws InstanceNotFoundException {
        return orderService.findById(id);
    }

    @PostMapping("/orders")
    public Order saveNew(final @RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/orders/{id}")
    public Order update(
            final @PathVariable String id,
            final @RequestBody Order order) throws AccessException {
        if (!id.equals(order.getOrderId())) {
            throw new AccessException("Order uri path doesn't match order id");
            // TODO : add custom exception handling

        }
        return orderService.save(order);
    }

    @DeleteMapping("/orders/{id}")
    public boolean deleteById(final @PathVariable String id) {
        return orderService.delete(id);
    }
}
