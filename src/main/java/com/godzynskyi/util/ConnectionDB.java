package com.godzynskyi.util;

import com.godzynskyi.properties.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class ConnectionDB {

    private static String DB_DRIVER = DataSource.getInstance().getProperty(DataSource.DB_DRIVER);
    private static String DB_CONNECTION = DataSource.getInstance().getProperty(DataSource.DB_CONNECTION);
    private static String DB_USER = DataSource.getInstance().getProperty(DataSource.DB_USER);
    private static String DB_PASSWORD = DataSource.getInstance().getProperty(DataSource.DB_PASSWORD);

    static Connection createDBConnection() {
        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return new MyConnection(dbConnection);
    }

}
