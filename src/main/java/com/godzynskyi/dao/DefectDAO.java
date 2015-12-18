package com.godzynskyi.dao;

import com.godzynskyi.entity.Defect;
import com.godzynskyi.factory.UtilFactory;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class DefectDAO {
    private static final Logger logger = Logger.getLogger(DefectDAO.class);
    private static final String addDefectQuery = "INSERT INTO defect (client_id, description, client_price, occurrence, car_id) VALUES (?,?,?,?,?)";

    public boolean addDefect(Defect defect) {
        try (Connection c = UtilFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(addDefectQuery)) {
            ps.setInt(1, defect.getClientId());
            ps.setString(2, defect.getDescription());
            ps.setFloat(3, defect.getPriceForClient());
            ps.setDate(4, new Date(defect.getDate().getTime()));
            ps.setInt(5, defect.getCarId());
            int i = ps.executeUpdate();
            return (i==1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
