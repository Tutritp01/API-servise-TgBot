package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Order;
import com.tutrit.persistence.core.persistence.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Component
public final class OrderClient {

    @Autowired(required = false)
    private OrderPersistence orderPersistence;

    public List<Order> findAll() {
        return orderPersistence.findAll();
    }

    public Order findById(final String id) throws InstanceNotFoundException {
        return orderPersistence.findById(id)
                .orElseThrow(InstanceNotFoundException::new);
    }

    public Order save(final Order order) {
        return orderPersistence.save(order);
    }

    public boolean delete(final String orderId) {
        return orderPersistence.delete(orderId);
    }
}
