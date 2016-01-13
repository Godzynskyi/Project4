package com.godzynskyi.util;

import com.godzynskyi.model.Order;
import org.apache.log4j.Logger;

import java.util.*;

public class ReservedDatesOfCarOLD {
    private static final Logger logger = Logger.getLogger(ReservedDatesOfCarOLD.class);
    private Map<Integer, Set<Order>> reserves = new HashMap<>();
    static private final ReservedDatesOfCarOLD instance = new ReservedDatesOfCarOLD();

    private ReservedDatesOfCarOLD() {
    }

    public static void addOrderToReserve(int carId, Order order) {
        Set<Order> dates = getDates(carId);

        synchronized (ReservedDatesOfCarOLD.class) {
            dates.add(order);
        }
    }

    public static Set<Order> getDatesOfCar(int carId) {
        return Collections.unmodifiableSet(getDates(carId));
    }

    private static Set<Order> getDates(int carId) {
        if (!instance.reserves.containsKey(carId)) {
            instance.reserves.put(carId, new HashSet<Order>());
        }
        return instance.reserves.get(carId);
    }

    public static boolean isAvailableDate(Calendar from, Calendar to, int carId) {
        if (!instance.reserves.containsKey(carId)) return true;
        Set<Order> reservedDates = instance.reserves.get(carId);
        for(Order order: reservedDates) {
            if (order.getStart().before(to) && order.getEnd().after(from)) return false;
        }
        return true;
    }

    public static void removeDatesOfCar(int carId, Order order) {
        Set<Order> orders = getDates(carId);

        synchronized (ReservedDatesOfCarOLD.class) {
            //TODO
            for(Order o: orders) {
                if(o.equals(order)) {
                    orders.remove(o);
                    return;
                }
            }
        }
    }
}