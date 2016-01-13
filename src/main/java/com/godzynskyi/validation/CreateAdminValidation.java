package com.godzynskyi.validation;

import com.godzynskyi.properties.Message;

/**
 * Created by Java Developer on 05.12.2015.
 */
public class CreateAdminValidation {
    public static class Result {
        public boolean noData = false;
        public boolean valid = true;
        public String error="";
    }

    public static Result isValid(String login, String password1, String password2, String firstname, String lastname) {
        Result result;
        result = new Result();
        if (login == null && password1 == null && password2 == null && firstname == null && lastname == null) {
            result.noData = true;
            return result;
        }

        if (login == null || password1 == null || password2 == null || firstname == null || lastname == null) {
            result.valid = false;
            result.error += Message.get(Message.NOT_ALL_PARAMETERS) + "\r\n";
        }

        if (!password1.equals(password2)) {
            result.valid = false;
            result.error += Message.get(Message.PASSWORDS_ARE_NOT_EQUALS) + "\r\n";
        }

//        if (DAOFactory.adminDAO().isAdminExist(login)) {
//            result.valid = false;
//            result.error += Message.getInstance().getProperty(Message.ADMIN_IS_EXIST);
//
//        }

        return result;
    }
}
