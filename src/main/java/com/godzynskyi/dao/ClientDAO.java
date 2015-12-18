package com.godzynskyi.dao;

import com.godzynskyi.entity.Client;
import com.godzynskyi.factory.UtilFactory;

import java.sql.*;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class ClientDAO {
    private static final String addClientQuery = "INSERT INTO client (email, firstname, lastname, phone) Values (?,?,?,?)";

    /**
     * Add Client to DB
     *
     * @param client to add to DB
     * @return Id of client or -1 if something wrong.
     */
    public int addClient(Client client) {
        try (Connection c = UtilFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(addClientQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getEmail());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getPhone());
            int i = ps.executeUpdate();
            if (i!=0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
