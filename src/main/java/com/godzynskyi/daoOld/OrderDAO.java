package com.godzynskyi.daoOld;

import com.godzynskyi.daoOld.order.filters.OrderFilter;
import com.godzynskyi.entity.Order;

import java.util.List;

/**
 * Created by Java Developer on 28.11.2015.
 */
public interface OrderDAO {

    /**
     * Add order to DB. adminId = -1.
     * @param order Adding order.
     * @return orderId from DB or -1 if Exception.
     */
    int addOrder(Order order);

    /**
     * Adds adminId to order.
     * @param orderId ID of order.
     * @param login login of admin.
     */
    void addAdminToOrder(int orderId, String login);

    /**
     * Returns order from DB by ID.
     * @param id Id from order.
     * @return Order from DB.
     */
    Order getOrder(int id);

    /**
     * Returns orders from DB fit filters conditions
     *
     * @param filter1 first OrderFilter
     * @param filters other OrderFilters
     * @return List of orders.
     */
    List<Order> getOrders(OrderFilter filter1, OrderFilter... filters);

}
