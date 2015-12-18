package com.godzynskyi.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Java Developer on 27.11.2015.
 */
public class EmailValidation {

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("[\\w]+[\\.\\w+]*['@'][\\w+\\.]+[\\w]{2,3}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
