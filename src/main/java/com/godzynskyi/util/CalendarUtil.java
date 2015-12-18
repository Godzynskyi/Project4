package com.godzynskyi.util;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class CalendarUtil {

    public static Calendar getCalendar(String date) {
        String[] dateSpl = date.split("-");
        int year = Integer.parseInt(dateSpl[0]);
        int month = Integer.parseInt(dateSpl[1]);
        int day = Integer.parseInt(dateSpl[2]);


        Calendar result = new GregorianCalendar(year, month-1, day);
        return result;
    }

    public static Calendar getCalendar(String date, String time) {
        String[] dateSpl = date.split("-");
        int year = Integer.parseInt(dateSpl[0]);
        int month = Integer.parseInt(dateSpl[1]);
        int day = Integer.parseInt(dateSpl[2]);

        String[] timeSpl = time.split(":");
        int hour = Integer.parseInt(timeSpl[0]);
        int minute = Integer.parseInt(timeSpl[1]);

        return new GregorianCalendar(year, month-1, day, hour, minute);
    }
}
