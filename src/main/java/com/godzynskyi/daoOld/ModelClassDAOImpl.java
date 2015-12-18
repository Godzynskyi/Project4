package com.godzynskyi.daoOld;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Java Developer on 30.11.2015.
 */
public class ModelClassDAOImpl implements ModelClassDAO {
    Connection connection;
    private static final Logger logger = Logger.getLogger("CarClassDAOImpl");

    public ModelClassDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addClassToModel(int modelId, int classId) {
        String query = "INSERT INTO model_class (model_id, class_id) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, modelId);
            ps.setInt(2, classId);

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
    public void removeClassCarDependency(int modelId, int classId) {
        String query = "DELETE FROM model_class WHERE model_id = ? and class_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, modelId);
            ps.setInt(2, classId);

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
}
