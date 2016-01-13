package com.godzynskyi.dao;

import com.godzynskyi.model.Defect;
import com.godzynskyi.data.source.DBFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class DefectDAO {
    private static final Logger logger = Logger.getLogger(DefectDAO.class);
    private static final String addDefectQuery =
            "INSERT INTO defect " +
            "(client_id, description, client_price, occurrence, car_id) " +
            "VALUES (?,?,?,?,?)";
    private static final String getDefectsOfCarQuery =
            "SELECT description FROM defect WHERE car_id = ?";

    protected DefectDAO() {}

    public boolean addDefect(Defect defect) {
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(addDefectQuery)) {

            ps.setInt(1, defect.getClientId());
            ps.setString(2, defect.getDescription());
            ps.setFloat(3, defect.getPriceForClient());
            ps.setDate(4, new Date(defect.getDate().getTime()));
            ps.setInt(5, defect.getCarId());
            int i = ps.executeUpdate();
            return (i==1);
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public List<String> getDefectsOfCar(int carId) {
        List<String> result = new ArrayList<>();
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(getDefectsOfCarQuery)) {

            ps.setInt(1, carId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

}
