package com.godzynskyi.data.source;

import java.sql.Connection;

/**
 * Created by Java Developer on 26.11.2015.
 */
public class DBFactory {
    public static Connection getDBConnection() {
        return ConnectionPool.getInstance.getConnection();
    }

    public static void releaseDBConnection(Connection connection) {
        ConnectionPool.getInstance.releaseConnection(connection);
    }
}
