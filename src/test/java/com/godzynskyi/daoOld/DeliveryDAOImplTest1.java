package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.UtilFactory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class DeliveryDAOImplTest1 extends Assert{
    int id=-1;
    DeliveryDAO deliveryDAO = new DeliveryDAOImpl(UtilFactory.getDBConnection());

    @Test
    public void testRemoveDelivery() throws Exception {
        deliveryDAO.removeDelivery(id);

        Order.Delivery delivery = deliveryDAO.getDelivery(id);

        assertEquals(null, delivery);
    }

    @Before
    public void testAddDelivery() throws Exception {
        Order.Delivery delivery = new Order.Delivery();
        delivery.setDate(new GregorianCalendar(2020, 11, 11, 14, 00));
        delivery.setAddress("Киев, ул. Механизаторов, 5-Б, кв.108");
        id = deliveryDAO.addDelivery(delivery);

    }
}