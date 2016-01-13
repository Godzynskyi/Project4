package com.godzynskyi.model;

import com.godzynskyi.util.CalendarUtil;

import java.util.Calendar;

public class TwoDatesWrapper {
    public Calendar first, second;

    public TwoDatesWrapper() {
    }

    public TwoDatesWrapper(Calendar first, Calendar second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return CalendarUtil.getDateString(first);
    }

    public String getSecond() {
        return CalendarUtil.getDateString(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwoDatesWrapper that = (TwoDatesWrapper) o;

        if (first != null ? !first.equals(that.first) : that.first != null) return false;
        if (second != null ? !second.equals(that.second) : that.second != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
