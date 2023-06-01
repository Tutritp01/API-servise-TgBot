package com.tutrit.persistence.core.persistence;

import com.tutrit.persistence.core.bean.Order;

import java.util.List;
import java.util.Optional;

public interface OrderPersistence {
    List<Order> findAll();
    Optional<Order> findById(String id);
    Order save(Order order);
    boolean delete(String orderId);
}
