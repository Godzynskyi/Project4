package com.godzynskyi.properties;

import java.util.ResourceBundle;

/**
 * Created by Java Developer on 04.12.2015.
 */
public class DataSource {
    private static DataSource instance = new DataSource();
    private ResourceBundle dataSource = ResourceBundle.getBundle("dataSource");

    public static final String DB_DRIVER = "DB_DRIVER";
    public static final String DB_CONNECTION = "DB_CONNECTION";
    public static final String DB_USER = "DB_USER";
    public static final String DB_PASSWORD = "DB_PASSWORD";

    private DataSource(){}

    public static DataSource getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return (String) dataSource.getObject(key);
    }
}
