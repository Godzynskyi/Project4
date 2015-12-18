package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Defect;
import org.apache.log4j.Logger;

import java.sql.*;

public class DefectsDAOImpl implements DefectsDAO {

    private Connection connection;

    private static final Logger logger = Logger.getLogger(DefectsDAOImpl.class);

    private static String QUERY = "INSERT INTO defect " +
            "(client_id, description, client_price, occurrence_date, car_id)" +
            "VALUES (?,?,?,?,?)";

    public DefectsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addDefect(Defect defect) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(QUERY);
            ps.setInt(1, defect.getClientId());
            ps.setString(2, defect.getDescription());
            ps.setFloat(3, defect.getPriceForClient());
            ps.setDate(4, new Date(defect.getDate().getTime()));
            ps.setInt(5, defect.getCarId());
            int i = ps.executeUpdate();
        }catch (SQLException e) {
            logger.error(e);
        }
    }
}
