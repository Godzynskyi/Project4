package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Client;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Java Developer on 27.11.2015.
 */
public class ClientDAOImpl implements ClientDAO {
    Connection connection;
    private static final Logger logger = Logger.getLogger("ClientDAOImpl");

    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client getClient(int id) {
        String query = "SELECT email, firstname, lastname, phone, passport FROM client WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Client result = new Client();
                result.setId(id);
                result.setEmail(resultSet.getString(1));
                result.setFirstName(resultSet.getString(2));
                result.setLastName(resultSet.getString(3));
                result.setPhone(resultSet.getString(4));
                return result;
            }
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
        return null;
    }

    @Override
    public int addClient(Client client) {
        String query = "INSERT INTO client (email, firstname, lastname, phone) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getEmail());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getPhone());
            int i = ps.executeUpdate();
            if (i != 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
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
        return -1;
    }

}
