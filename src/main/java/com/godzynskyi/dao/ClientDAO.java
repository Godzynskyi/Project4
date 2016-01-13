package com.godzynskyi.dao;

import com.godzynskyi.model.Client;
import com.godzynskyi.data.source.DBFactory;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class ClientDAO {
    private static final Logger logger = Logger.getLogger(ClientDAO.class);

    private static final String addClientQuery =
            "INSERT INTO client " +
                    "(email, firstname, lastname, phone) VALUES (?,?,?,?)";
    private static final String updateClientQuery =
            "update client " +
                    "set email = ?, phone = ?, firstname = ?, lastname = ? " +
                    "WHERE id = ?";

    protected ClientDAO() {}

    /**
     * Add Client to DB
     *
     * @param client to add to DB
     * @return Id of client or -1 if something wrong.
     */
    public int addClient(Client client) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(addClientQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getEmail());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getPhone());
            int i = ps.executeUpdate();
            if (i != 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateClient(Client client) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(updateClientQuery)) {

            ps.setString(1, client.getEmail());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getFirstName());
            ps.setString(4, client.getLastName());
            ps.setInt(5, client.getId());

            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }
}
