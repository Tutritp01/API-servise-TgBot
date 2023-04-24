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

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order findAll(@PathVariable String id) throws InstanceNotFoundException {
        return orderService.findById(id);
    }

    @PostMapping("/orders")
    public Order saveNew(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/orders/{id}")
    public Order update(@PathVariable String id, @RequestBody Order order) throws AccessException {
        if (!id.equals(order.getOrderId())) {
            throw new AccessException("Order uri path doesn't match order id"); // TODO: 3/26/23 add custom exception
        }
        return orderService.save(order);
    }

    @DeleteMapping("/orders/{id}")
    public boolean deleteById(@PathVariable String id) {
        return orderService.delete(id);
    }
}
