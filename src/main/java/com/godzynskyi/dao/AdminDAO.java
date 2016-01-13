package com.godzynskyi.dao;

import com.godzynskyi.model.Admin;
import com.godzynskyi.data.source.DBFactory;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class AdminDAO {
    static String isAdminQuery =
            "SELECT password FROM admin WHERE login = ?";
    static String addAdminQuery =
            "INSERT ignore INTO admin " +
            "(login, password, firstname, lastname) " +
            "VALUES (?, ?, ?, ?)";

    protected AdminDAO() {}

    private static final Logger logger = Logger.getLogger(AdminDAO.class);

    public boolean isAdmin(String login, String password) {
        try (Connection connection = DBFactory.getDBConnection();
        PreparedStatement ps = connection.prepareStatement(isAdminQuery)) {
            ps.setString(1, login.toLowerCase());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return BCrypt.checkpw(password, resultSet.getString(1));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    /**
     * 
     * @param admin Admin object
     * @return 1 if added, 0 if exists, -1 if SQLException
     */
    public int addAdmin(Admin admin) {
        try (Connection c = DBFactory.getDBConnection();
                PreparedStatement ps = c.prepareStatement(addAdminQuery)) {
            ps.setString(1, admin.getLogin().toLowerCase());
            String hashedPass = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
            ps.setString(2, hashedPass);
            ps.setString(3, admin.getFirstName());
            ps.setString(4, admin.getLastName());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            logger.error(e);
            return -1;
        }
    }
}
