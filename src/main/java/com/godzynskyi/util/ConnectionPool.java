package com.godzynskyi.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Connection pool to database. First pool initializes connections in the quantity of CAPACITY.
 * If there are free connections in this pull, pool will return one of this created connections.
 * If there are NO free connections, pool create new connection.
 *
 */
public class ConnectionPool {
    public static ConnectionPool getInstance = new ConnectionPool(10);
    private final int CAPACITY;
    List<Connection> connections;

    private ConnectionPool(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        connections = new ArrayList<Connection>();
        while (connections.size() < CAPACITY) {
            connections.add(createConnection());
        }
    }

    private Connection createConnection() {
        return ConnectionDB.createDBConnection();
    }

    /**
     *
     * @return free connection from pool
     * or new Connection if there are no free connections in the pool.
     */
    public Connection getConnection() {
        Connection res = null;
        while (true) {
            if (connections.size() == 0) {
                res = createConnection();
                return res;
            }
            synchronized (this) {
                if (connections.size() > 0) {
                    res = connections.get(0);
                    connections.remove(0);
                    return res;
                }
            }
        }
    }

    /**
     * Add connection back to pool if it needs
     * @param connection connection that will not used
     */
    public void releaseConnection(Connection connection) {
        if (connections.size() >= CAPACITY) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        synchronized (this) {
            if (connections.size() < CAPACITY) {
                connections.add(connection);
            }
        }
    }
}
