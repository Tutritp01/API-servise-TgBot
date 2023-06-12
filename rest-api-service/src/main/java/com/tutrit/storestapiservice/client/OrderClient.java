package com.tutrit.storestapiservice.client;

import com.tutrit.persistence.core.bean.Order;
import com.tutrit.persistence.core.persistence.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Component
public class OrderClient {

    @Autowired(required = false)
    private OrderPersistence orderPersistence;

    /**
     * Retrieves all orders.
     *
     * <p>This method can be overridden by subclasses to provide
     * custom behavior for retrieving all orders.
     * If you extend this class and override this method, make
     * sure to document any specific behavior or considerations.</p>
     *
     * @return The list of all orders.
     */
    public List<Order> findAll() {
        return orderPersistence.findAll();
    }

    /**
     * Finds an order by its ID.
     *
     * <p>This method can be overridden by subclasses to provide
     * custom behavior for finding orders by ID.
     * If you extend this class and override this method, make
     * sure to document any specific behavior or considerations.</p>
     *
     * @param id The ID of the order to find.
     * @return The found order.
     * @throws InstanceNotFoundException if no order with the
     * specified ID is found.
     */
    public Order findById(final String id) throws InstanceNotFoundException {
        return orderPersistence.findById(id)
                .orElseThrow(InstanceNotFoundException::new);
    }

    /**
     * Saves the specified order.
     *
     * <p>This method can be overridden by subclasses to provide
     * custom behavior for saving orders.
     * If you extend this class and override this method, make sure
     * to document any specific behavior or considerations.</p>
     *
     * @param order The order to be saved.
     * @return The saved order.
     */
    public Order save(final Order order) {
        return orderPersistence.save(order);
    }

    /**
     * Deletes an order by its ID.
     *
     * <p>This method can be overridden by subclasses to provide custom
     * behavior for deleting orders by ID.
     * If you extend this class and override this method, make sure to
     * document any specific behavior or considerations.</p>
     *
     * @param orderId The ID of the order to delete.
     * @return {@code true} if the order was successfully deleted,
     * {@code false} otherwise.
     */
    public boolean delete(final String orderId) {
        return orderPersistence.delete(orderId);
    }
}
