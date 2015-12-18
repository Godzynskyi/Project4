package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.factory.UtilFactory;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class OrderDAOImplTest {

    @Test
    public void testAddOrder() throws Exception {
        Exception ex = null;

        Order order = new Order();
        order.setId(-1);
        order.setCar(DAOFactory.carDAO(UtilFactory.getDBConnection()).getCar(7));
        order.setClientId(1);
        Calendar start = new GregorianCalendar(2015, 12, 15);
        Calendar end = new GregorianCalendar(2015, 12, 18);
        order.setStart(start);
        order.setEnd(end);
        order.setChildChair(true);
        order.setGps(false);
        order.setDetails("kjfyuk");

        try {
            int id = DAOFactory.orderDAO(UtilFactory.getDBConnection()).addOrder(order);
        } catch (Exception e) {
            ex = e;
        }

        assertEquals(null, ex);
    }

    @Test
    public void testAddAdminToOrder() throws Exception {
        Exception ex = null;

        try {
            OrderDAO orderDAO = DAOFactory.orderDAO(UtilFactory.getDBConnection());
            orderDAO.addAdminToOrder(2, "ivan");

        } catch (Exception e) {
            ex = e;
        }

        assertEquals(null, ex);

    }

    @Test
    public void testGetOrder() throws Exception {
        Order order = null;

        OrderDAO orderDAO = DAOFactory.orderDAO(UtilFactory.getDBConnection());
        order = orderDAO.getOrder(2);

        assertEquals(2, order.getId());
    }
}