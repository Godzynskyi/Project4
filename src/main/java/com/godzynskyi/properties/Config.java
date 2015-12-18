package com.godzynskyi.properties;

import java.util.ResourceBundle;

/**
 * Created by Java Developer on 03.12.2015.
 */
public class Config {

    private static Config instance = new Config();
    private ResourceBundle config = ResourceBundle.getBundle("config");
    public static final String CONTROLLERS_PACKAGE = "CONTROLLERS_PACKAGE";


    public static final String SERVLET_PREFIX = "SERVLET_PREFIX";
    public static final String LOGIN_PAGE = "LOGIN_PAGE";
    public static final String GPS_PRICE = "GPS_PRICE";
    public static final String CHILD_CHAIR_PRICE = "CHILD_CHAIR_PRICE";


    private Config() {}

    public static Config getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return (String) config.getObject(key);
    }
}
