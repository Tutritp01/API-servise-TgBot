package com.tutrit.persistence;

import com.tutrit.persistence.core.bean.Order;
import com.tutrit.persistence.core.persistence.OrderPersistence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Component
public final class InMemoryOrderPersistence implements OrderPersistence {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(2);

    { // TODO : Load default data conditionally from file
        orders.put("0", new Order("0", null, null, null, null, "new"));
        orders.put("1", new Order("1", null, null, null, null, "in progress"));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Optional<Order> findById(final String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public Order save(final Order order) {
        final AtomicReference<Order> atomicOrder = new AtomicReference<>();
        Optional.ofNullable(order.getOrderId()).ifPresentOrElse(
                orderId -> atomicOrder.set(handleUpdate(orderId, order)),
                () -> atomicOrder.set(handleCreate(order))
        );
        return atomicOrder.get();

    }

    @Override
    public boolean delete(final String orderId) {
        return Optional.ofNullable(orders.remove(orderId)).isPresent();
    }

    private Order handleUpdate(final String orderId, final Order order) {
        if (orders.get(orderId) != null) {
            return orders.put(orderId, order);
        }

        // TODO : Add custom exception to core

        throw new RuntimeException(
                "Order cannot been update because it is not exist. "
                        + "Save order first");
    }

    private Order handleCreate(final Order order) {
        String orderId = String.valueOf(this.id.getAndIncrement());
        order.setOrderId(orderId);
        return orders.put(orderId, order);
    }

}
