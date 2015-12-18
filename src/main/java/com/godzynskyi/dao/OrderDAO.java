package com.godzynskyi.dao;

import com.godzynskyi.dao.order.filters.NoAdminFilter;
import com.godzynskyi.daoOld.order.filters.OrderFilter;
import com.godzynskyi.entity.Car;
import com.godzynskyi.entity.Client;
import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.UtilFactory;
import com.godzynskyi.properties.Config;
import com.godzynskyi.util.CalendarUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class OrderDAO {
    private static final Logger logger = Logger.getLogger(OrderDAO.class);
    private static final String getOrdersFilterQuery = "SELECT _order.id, admin_login, client_id, start_date, end_date, details, child_chair, gps, car.id, car.model, car.price FROM _order inner join car on car.id = _order.car_id where ?";
    private static final String getOrderByIdQuery = "SELECT admin_login, start_date, end_date, details, child_chair, gps, client.id, client.email, client.firstname, client.lastname, client.phone, car.id, car.model, car.price FROM _order inner JOIN car on car.id=_order.car_id inner join client on client.id=_order.client_id where _order.id = ?";

    /**
     *
     * @param orderFilters
     * @return object of Order where Car has only ID, model and price, Client has only id.
     */
    public List<Order> getOrdersWithoutClientAndCar(List<OrderFilter> orderFilters) {
        List<Order> res = new ArrayList<>();
        try (Connection c = UtilFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(getOrdersFilterQuery)) {

            StringBuilder sb = new StringBuilder();
            switch (orderFilters.size()) {
                case 0:
                    sb.append(new NoAdminFilter().stringPattern());
                    break;
                case 1:
                    sb.append(orderFilters.get(0).stringPattern());
                    break;
                default:
                    sb.append(orderFilters.remove(0).stringPattern());
                    for (OrderFilter of : orderFilters) {
                        sb.append(" and ")
                                .append(of.stringPattern());
                    }
            }

            ps.setString(1, sb.toString());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Car.Builder carBuilder = new Car().newBuilder();
                Car car = carBuilder.setId(rs.getInt(9))
                        .setModel(rs.getString(10))
                        .setPrice(rs.getInt(11))
                        .build();

                Order order = Order.newBuilder().setId(rs.getInt(1))
                        .setAdmin(rs.getString(2))
                        .setClient(new Client(rs.getInt(3)))
                        .setStart(CalendarUtil.getCalendar(rs.getString(4)))
                        .setEnd(CalendarUtil.getCalendar(rs.getString(5)))
                        .setDetails(rs.getString(6))
                        .setChildChair(rs.getBoolean(7))
                        .setGps(rs.getBoolean(8))
                        .setCar(car)
                        .setPrice(car.getPrice())
                        .build();

                res.add(order);
            }
            return res;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public Order getOrder(int idOrder) {
        Order res = null;
        try (Connection c = UtilFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(getOrderByIdQuery)) {
            ps.setInt(1, idOrder);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return res;
            Order.Builder orderBuilder = res.newBuilder()
                    .setAdmin(rs.getString(1))
                    .setStart(CalendarUtil.getCalendar(rs.getString(2)))
                    .setEnd(CalendarUtil.getCalendar(rs.getString(3)))
                    .setDetails(rs.getString(4))
                    .setChildChair(rs.getBoolean(5))
                    .setGps(rs.getBoolean(6));

            Client client = Client.newBuilder()
                    .setId(rs.getInt(7))
                    .setEmail(rs.getString(8))
                    .setFirstName(rs.getString(9))
                    .setLastName(rs.getString(10))
                    .setPhone(rs.getString(11))
                    .build();

            Car car = Car.newBuilder()
                    .setId(rs.getInt(12))
                    .setModel(rs.getString(13))
                    .setPrice(rs.getInt(14))
                    .build();

            int price = car.getPrice();
            if (res.isChildChair()) price += Integer.parseInt(Config.getInstance().getProperty(Config.CHILD_CHAIR_PRICE));
            if (res.isGps()) price += Integer.parseInt(Config.getInstance().getProperty(Config.GPS_PRICE));


            res = orderBuilder.setClient(client)
                    .setCar(car)
                    .setPrice(price)
                    .build();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Adding Order to DB.
     *
     * @param order
     * @return orderId if created or -1 otherwise
     */
    public int addOrder(Order order) {
        //TODO
        return -1;
    }
}
