package com.godzynskyi.factory;

import com.godzynskyi.util.ConnectionPool;

import java.sql.Connection;

/**
 * Created by Java Developer on 26.11.2015.
 */
public class UtilFactory {
    public static Connection getDBConnection() {
        return ConnectionPool.getInstance.getConnection();
    }

    public static void releaseDBConnection(Connection connection) {
        ConnectionPool.getInstance.releaseConnection(connection);
    }
}
