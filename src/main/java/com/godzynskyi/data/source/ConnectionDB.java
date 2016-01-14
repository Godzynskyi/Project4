package com.godzynskyi.data.source;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class ConnectionDB {
    private static final Logger logger = Logger.getLogger(ConnectionDB.class);

    static Connection createDBConnection() {

        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/cars");
            Connection dbConnection = ds.getConnection();
            return new MyConnection(dbConnection);
        } catch (NamingException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

}
