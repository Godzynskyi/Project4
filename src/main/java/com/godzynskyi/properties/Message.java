package com.godzynskyi.properties;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

/**
 * Created by Java Developer on 03.12.2015.
 */
public class Message {

    private static Message instance = new Message();
    private ResourceBundle resource= ResourceBundle.getBundle(BUNDLE_NAME);
    private static final String BUNDLE_NAME = "messages.messages";

    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";
    public static final String NOT_ALL_PARAMETERS = "NOT_ALL_PARAMETERS";
    public static final String PASSWORDS_ARE_NOT_EQUALS = "PASSWORDS_ARE_NOT_EQUALS";
    public static final String ADMIN_IS_EXIST = "ADMIN_IS_EXIST";
    public static final String ADMIN_SUCCESSFULLY_ADDED = "ADMIN_SUCCESSFULLY_ADDED";
    public static final String BAD_PHONE_NUMBER = "BAD_PHONE_NUMBER";
    public static final String BAD_EMAIL = "BAD_EMAIL";
    public static final String SQL_EXCEPTION = "SQL_EXCEPTION";
    public static final String CAR_NOT_ADDED = "CAR_NOT_ADDED";
    public static final String CAR_WAS_ADDED = "CAR_WAS_ADDED";
    public static final String BAD_DATA = "BAD_DATA";
    // ...

    private Message() {
    }

    public static Message getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
