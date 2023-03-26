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

    public List<Order> findAll() {
        return orderClient.findAll();
    }

    public Order findById(String id) throws InstanceNotFoundException {
        return orderClient.findById(id);
    }

    public Order save(Order order) {
        return orderClient.save(order);
    }

    public boolean delete(String orderId) {
        return orderClient.delete(orderId);
    }
}
