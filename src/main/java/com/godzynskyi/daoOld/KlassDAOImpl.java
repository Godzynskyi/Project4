package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Klass;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Java Developer on 27.11.2015.
 */
public class KlassDAOImpl implements KlassDAO {
    Connection connection;
    private static final Logger logger = Logger.getLogger("KlassDAOImpl");


    public KlassDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAllFromEnum() {
        String query = "INSERT INTO classes (name, description) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            for (Klass klass: Klass.values()) {
                if (getKlassId(klass.toString()) != -1) continue;
                ps.setString(1, klass.toString());
                ps.setString(2, klass.toString());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public int getKlassId(String klassName) {
        int res = -1;
        String query = "SELECT id FROM classes WHERE name = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, klassName);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return res;
    }
}
