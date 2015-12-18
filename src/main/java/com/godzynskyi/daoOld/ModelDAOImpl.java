package com.godzynskyi.daoOld;

import com.godzynskyi.factory.ServiceFactory;
import com.godzynskyi.entity.Model;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Java Developer on 24.11.2015.
 */
public class ModelDAOImpl implements ModelDAO {
    private final String getModelByIdQuery = "SELECT brand.name, model, about FROM model INNER JOIN brand ON brand.id = model.brand WHERE model.id = ?";
    private final String getModelIdByModelQuery = "SELECT model.id FROM model INNER JOIN brand ON brand.id = model.brand WHERE brand.name = ? AND model.model = ?";
    private final String addModelQuery = "INSERT INTO model (brand, model, about) VALUES(?, ?, ?)";
    private static final Logger logger = Logger.getLogger("ModelDAOImpl");


    Connection connection;

    public ModelDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Model getModel(int id) {
        Model res = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getModelByIdQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = new Model(resultSet.getString(1), resultSet.getString(2));
                res.setAbout(resultSet.getString(3));
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return res;
    }

    @Override
    public int getModelId(Model model) {
        int res = -1;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getModelIdByModelQuery);
            statement.setString(1, model.getBrand());
            statement.setString(2, model.getModel());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return res;
    }

    @Override
    public boolean addModel(Model model) {
        int brandId = ServiceFactory.brandService().getBrandId(model.getBrand());
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(addModelQuery);
            statement.setInt(1, brandId);
            statement.setString(2, model.getModel());
            statement.setString(3, model.getAbout());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return false;
    }

}
