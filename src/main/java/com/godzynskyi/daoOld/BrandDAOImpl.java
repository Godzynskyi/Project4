package com.godzynskyi.daoOld;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Java Developer on 24.11.2015.
 */
public class BrandDAOImpl implements BrandDAO {
    private final String getBrandIdQuery = "Select id from brand where brand.name = ?";
    private final String getBrandByIdQuery = "SELECT brand.name FROM brand WHERE id = ?";
    private final String addBrandQuery = "INSERT INTO brand (name) VALUES (?)";
    private static final Logger logger = Logger.getLogger("BrandDAOImpl");

    Connection connection;

    public BrandDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getBrandId(String brand) {
        int res = -1;
        try (PreparedStatement statement = connection.prepareStatement(getBrandIdQuery)) {
            statement.setString(1, brand);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getInt(1);
        } catch (SQLException e) {
            logger.error(e);
        }
        return res;
    }

    @Override
    public String getBrand(int id) {
        String res = "";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getBrandByIdQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) res = resultSet.getString(1);
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (statement!= null) try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return res;
    }

    @Override
    public boolean addBrand(String brand) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(addBrandQuery);
            statement.setString(1, brand);
            int res = statement.executeUpdate();
            if (res == 1) return true;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (statement!= null) try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return false;
    }
}
