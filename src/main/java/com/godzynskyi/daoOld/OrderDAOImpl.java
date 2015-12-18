package com.godzynskyi.daoOld;

import com.godzynskyi.daoOld.order.filters.OrderFilter;
import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.DAOFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Java Developer on 28.11.2015.
 */
public class OrderDAOImpl implements OrderDAO {
    Connection connection;
    private static final Logger logger = Logger.getLogger("OrderDAOImpl");


    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int addOrder(Order order) {
//        String query = "INSERT INTO _order (admin_id, client_id, car_id, start_date, end_date, details, child_chair, gps, delivery_id_to, delivery_id_from) VALUES (NULL ,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, order.getClientId());
//            ps.setInt(2, order.getCar().getId());
//            ps.setTimestamp(3, new Timestamp(order.getStart().getTimeInMillis()));
//            ps.setTimestamp(4, new Timestamp(order.getEnd().getTimeInMillis()));
//            ps.setString(5, order.getDetails());
//            ps.setBoolean(6, order.isChildChair());
//            ps.setBoolean(7, order.isGps());
//            if (order.getDeliveryStartId() == -1) {
//                ps.setNull(8, Types.INTEGER);
//            } else {
//                ps.setInt(8, order.getDeliveryStartId());
//            }
//
//            if (order.getDeliveryEndId() == -1) {
//                ps.setNull(9, Types.INTEGER);
//            } else {
//                ps.setInt(9, order.getDeliveryEndId());
//            }
//
//            int i = ps.executeUpdate();
//            if (i != 0) {
//                ResultSet generatedKeys = ps.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    int orderId = generatedKeys.getInt(1);
//                    order.setId(orderId);
//                }
//            }
//            return order.getId();
//        } catch (SQLException e) {
//            logger.error(e);
//            return -1;
//        } finally {
//            if (ps != null) try {
//                ps.close();
//            } catch (SQLException e) {
//                logger.error(e);
//            }
//        }
        return 0;
    }

    @Override
    public void addAdminToOrder(int orderId, String admin) {
        String query = "UPDATE _order SET admin_id = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, admin);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public Order getOrder(int id) {
//        String query = "SELECT admin_id, client_id, car_id, start_date, end_date, delivery_id_to, delivery_id_from, details, child_chair, gps FROM _order WHERE id = ?";
//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, id);
//            ResultSet resultSet = ps.executeQuery();
//            if (resultSet.next()) {
//                Order order = new Order();
//                order.setId(id);
//                order.setAdmin(resultSet.getString(1));
//                int clientId = resultSet.getInt(2);
//                int carId = resultSet.getInt(3);
//                Calendar calendar = new GregorianCalendar();
//                calendar.setTime(resultSet.getDate(4));
//                order.setStart(calendar);
//                calendar.setTime(resultSet.getDate(5));
//                order.setEnd(calendar);
//                int deliveryStart = resultSet.getInt(6);
//                int deliveryEnd = resultSet.getInt(7);
//                order.setDetails(resultSet.getString(8));
//                order.setChildChair(resultSet.getBoolean(9));
//                order.setGps(resultSet.getBoolean(10));
//
//                order.setClientId(clientId);
//                CarDAO carDAO = DAOFactory.carDAO(connection);
//                order.setCar(carDAO.getCar(carId));
//                order.setDeliveryStartId(deliveryStart);
//                order.setDeliveryEndId(deliveryEnd);
//                return order;
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//        } finally {
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    logger.error(e);
//                }
//            }
//        }
        return null;
    }

    @Override
    public List<Order> getOrders(OrderFilter filter1, OrderFilter... filters) {
//        StringBuilder query = new StringBuilder();
//        query.append("Select admin_id, client_id, car_id, start_date, end_date, delivery_id_to, delivery_id_from, details, child_chair, gps, id ")
//                .append("FROM _order WHERE ")
//                .append(filter1.stringPattern());
//        for (OrderFilter orderFilter: filters) {
//            query.append(" AND ")
//                    .append(orderFilter.stringPattern());
//        }
//
//        Statement st = null;
//        try {
//            st = connection.createStatement();
//            ResultSet resultSet = st.executeQuery(query.toString());
//            List<Order> result = new ArrayList<Order>();
//            while (resultSet.next()) {
//                Order order = new Order();
//                order.setAdmin(resultSet.getString(1));
//                int clientId = resultSet.getInt(2);
//                int carId = resultSet.getInt(3);
//                Calendar calendar = new GregorianCalendar();
//                calendar.setTime(resultSet.getDate(4));
//                order.setStart(calendar);
//                calendar.setTime(resultSet.getDate(5));
//                order.setEnd(calendar);
//                int deliveryStart = resultSet.getInt(6);
//                int deliveryEnd = resultSet.getInt(7);
//                order.setDetails(resultSet.getString(8));
//                order.setChildChair(resultSet.getBoolean(9));
//                order.setGps(resultSet.getBoolean(10));
//                order.setId(resultSet.getInt(11));
//
//                order.setClientId(clientId);
//                CarDAO carDAO = DAOFactory.carDAO(connection);
//                order.setCar(carDAO.getCar(carId));
//                DeliveryDAO deliveryDAO = DAOFactory.deliveryDAO(connection);
//                order.setDeliveryStartId(deliveryStart);
//                order.setDeliveryEndId(deliveryEnd);
//
//                result.add(order);
//            }
//            if (result.size() != 0) return result;
//
//        } catch (SQLException e) {
//            logger.error(e);
//        } finally {
//            if (st != null) {
//                try {
//                    st.close();
//                } catch (SQLException e) {
//                    logger.error(e);
//                }
//            }
//        }
        return null;
    }

}
