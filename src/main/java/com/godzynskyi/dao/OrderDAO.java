package com.godzynskyi.dao;

import com.godzynskyi.dao.order.filters.NoAdminFilter;
import com.godzynskyi.dao.order.filters.OrderFilter;
import com.godzynskyi.model.Car;
import com.godzynskyi.model.Client;
import com.godzynskyi.model.Order;
import com.godzynskyi.util.CalendarUtil;
import com.godzynskyi.data.source.DBFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class OrderDAO {
    private static final Logger logger = Logger.getLogger(OrderDAO.class);
    private static final String getOrdersFilterQuery = "SELECT _order.id, admin_login, client_id, start_date, end_date, " +
            "details, child_chair, gps, car.id, car.model, " +
            "_order.total_price, _order.status " +
            "FROM _order " +
            "inner join car on car.id = _order.car_id " +
            "where _order.status != 3 and ";
    private static final String getOrderByIdQuery =
            "SELECT admin_login, start_date, end_date, details, child_chair, " +
                    "gps, client.id, client.email, client.firstname, client.lastname, " +
                    "client.phone, car.id, car.model, _order.total_price, status " +
                    "FROM _order " +
                    "inner JOIN car on car.id = _order.car_id " +
                    "inner join client on client.id = _order.client_id " +
                    "where _order.id = ?";
    private static final String addOrderQuery =
            "insert into _order " +
                    "(admin_login, client_id, car_id, start_date, end_date, " +
                    "details, child_chair, gps, total_price) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String updateOrderQuery = "update _order " +
            "set admin_login = ?, car_id=?, start_date = ?, end_date = ?, details = ?, child_chair = ?, gps = ? " +
            "WHERE id = ?";
    private static final String isAvailableDateForCarQuery =
            "SELECT id FROM _order " +
                    "where car_id = ? and start_date < ? and end_date > ? limit 1";
    private static final String changeOrderStatusQuery =
            "UPDATE _order set status = ? where id = ?";
    private static final String getBlockedDatesQuery =
            "SELECT start_date, end_date " +
                    "from _order " +
                    "where _order.car_id = ? " +
                    "and end_date>= ? and start_date <= ?";
    private static final String changeAdminOfOrderQuery = "UPDATE _order SET admin_login = ? WHERE id = ?";
    private static final String deleteOrderQuery = "DELETE FROM _order where id = ?";

    protected OrderDAO() {}

    /**
     * @param orderFilters
     * @return object of Order where Car has only ID, model and price, Client has only id.
     */
    public List<Order> getOrdersWithoutClientAndCar(List<OrderFilter> orderFilters) {
        List<Order> res = new ArrayList<>();
        try (Connection c = DBFactory.getDBConnection();
             Statement statement = c.createStatement()) {

            StringBuilder sb = new StringBuilder(getOrdersFilterQuery);
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

            ResultSet rs = statement.executeQuery(sb.toString());
            while (rs.next()) {
                Car.Builder carBuilder = new Car().newBuilder();
                Car car = carBuilder.setId(rs.getInt(9))
                        .setModel(rs.getString(10))
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
                        .setPrice(rs.getInt(11))
                        .setStatus(rs.getInt(12))
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
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(getOrderByIdQuery)) {
            ps.setInt(1, idOrder);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return res;

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
                    .build();

            res = Order.newBuilder()
                    .setId(idOrder)
                    .setAdmin(rs.getString(1))
                    .setStart(CalendarUtil.getCalendar(rs.getString(2)))
                    .setEnd(CalendarUtil.getCalendar(rs.getString(3)))
                    .setDetails(rs.getString(4))
                    .setChildChair(rs.getBoolean(5))
                    .setGps(rs.getBoolean(6))
                    .setStatus(rs.getInt(15))
                    .setClient(client)
                    .setCar(car)
                    .setPrice(rs.getInt(14))
                    .build();

            return res;
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    public boolean changeAdminOfOrder(int orderId, String adminLogin) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(changeAdminOfOrderQuery)) {

            ps.setString(1, adminLogin);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public boolean changeOrderStatus(int orderId, Order.Status status) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(changeOrderStatusQuery)) {

            ps.setInt(1, status.getStatus());
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public boolean isAvailableDateForCar(Calendar start, Calendar end, int carId) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(isAvailableDateForCarQuery)) {

            ps.setInt(1, carId);
            ps.setDate(2, new Date(end.getTimeInMillis()));
            ps.setDate(3, new Date(start.getTimeInMillis()));

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public boolean deleteOrder(int orderId) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(deleteOrderQuery)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * Adding Order to DB.
     * If there are problems with DB return -1.
     *
     * @param order
     * @return orderId if created or -1 otherwise
     */
    public int addOrder(Order order) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(addOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setNull(1, Types.VARCHAR);
            ps.setInt(2, order.getClient().getId());
            ps.setInt(3, order.getCar().getId());
            ps.setString(4, CalendarUtil.getDateString(order.getStart()));
            ps.setString(5, CalendarUtil.getDateString(order.getEnd()));
            ps.setString(6, order.getDetails());
            ps.setBoolean(7, order.isChildChair());
            ps.setBoolean(8, order.isGps());
            ps.setInt(9, order.getPrice());

            int i = ps.executeUpdate();
            if (i == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return -1;
    }

    /**
     * Returns dates from ... to ... when this car was reserved.
     *
     * @param carId Id od car,
     * @return set of dates or null if there was problems with DB.
     */
    public Set<Order> getBlockedDates(int carId) {
        Set<Order> res = new HashSet<>();
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(getBlockedDatesQuery)) {

            Calendar to = new GregorianCalendar();
            to.add(Calendar.MONTH, 3);

            ps.setInt(1, carId);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setDate(3, new Date(to.getTimeInMillis()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setStart(CalendarUtil.getCalendar(rs.getString(1)));
                order.setEnd(CalendarUtil.getCalendar(rs.getString(2)));
                res.add(order);
            }
            return res;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public boolean updateOrder(Order order) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(updateOrderQuery)) {

            ps.setString(1, order.getAdmin());
            ps.setInt(2, order.getCar().getId());
            ps.setString(3, CalendarUtil.getDateString(order.getStart()));
            ps.setString(4, CalendarUtil.getDateString(order.getEnd()));
            ps.setString(5, order.getDetails());
            ps.setBoolean(6, order.isChildChair());
            ps.setBoolean(7, order.isGps());
            ps.setInt(8, order.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }
}
