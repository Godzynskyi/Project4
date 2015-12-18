package com.godzynskyi.validation;

import com.godzynskyi.properties.Message;

/**
 * Created by Java Developer on 06.12.2015.
 */
public class CreateOrderValidation {

    public static class Result {
        public boolean noData = false;
        public boolean valid = true;
        public String error = "";
    }

    public static Result isValid(String carId, String dateFrom, String dateTo, String phone, String email) {
        Result result = new Result();
        if (dateFrom == null && dateTo == null && phone == null && email == null) {
            result.noData = true;
            return result;
        }

        if (carId == null || dateFrom == null || dateTo == null || phone == null) {
            result.valid = false;
            result.error = Message.getInstance().getProperty(Message.NOT_ALL_PARAMETERS);
            return result;
        }

        if (!PhoneValidation.isPhone(phone)) {
            result.valid = false;
            result.error = Message.getInstance().getProperty(Message.BAD_PHONE_NUMBER);
        }

        if (!EmailValidation.isEmail(email)) {
            result.error += "<br>" + Message.getInstance().getProperty(Message.BAD_EMAIL);
        }

        return result;
    }
}
