package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Admin;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Java Developer on 28.11.2015.
 */
public class AdminDAOImpl implements AdminDAO {
    Connection connection;
    private static final Logger logger = Logger.getLogger("AdminDAOImpl");

    public AdminDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        String query = "INSERT INTO admin (login, password, firstname, lastname) VALUES (?, ?, ?, ?) where not EXISTS (SELECT * from admin where login = ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, admin.getLogin().toLowerCase());
            String hashedPass = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
            ps.setString(2, hashedPass);
            ps.setString(3, admin.getFirstName());
            ps.setString(4, admin.getLastName());
            ps.setString(5, admin.getLogin().toLowerCase());
            int i = ps.executeUpdate();
            return (i > 0);

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean isAdmin(String login, String password) {
        String query = "SELECT password FROM admin WHERE login = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
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

    @Override
    public Admin getAdmin(String login) {
        String query = "Select login, password, firstname, lastname from admin where login = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, login);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setLogin(resultSet.getString(1));
                admin.setPassword(resultSet.getString(2));
                admin.setFirstName(resultSet.getString(3));
                admin.setLastName(resultSet.getString(4));
                return admin;
            } else {
                Admin admin = new Admin();
                admin.setLogin(null);
                return admin;
            }

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}
