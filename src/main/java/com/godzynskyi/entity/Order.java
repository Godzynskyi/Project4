package com.godzynskyi.entity;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class Order {

    int id;
    Client client;
    String admin;
    Car car;
    Calendar start;
    Calendar end;
    boolean gps, childChair;
    String details;
    int price;

    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            Order.this.id = id;
            return this;
        }

        public Builder setClient(Client client) {
            Order.this.client = client;
            return this;
        }

        public Builder setAdmin(String admin) {
            Order.this.admin = admin;
            return this;
        }

        public Builder setCar(Car car) {
            Order.this.car = car;
            return this;
        }

        public Builder setStart(Calendar start) {
            Order.this.start = start;
            return this;
        }

        public Builder setEnd(Calendar end) {
            Order.this.end = end;
            return this;
        }

        public Builder setGps(boolean gps) {
            Order.this.gps = gps;
            return this;
        }

        public Builder setChildChair(boolean childChair) {
            Order.this.childChair = childChair;
            return this;
        }

        public Builder setDetails(String details) {
            Order.this.details = details;
            return this;
        }

        public Builder setPrice(int price) {
            Order.this.price = price;
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }

    public static Builder newBuilder() {
        return new Order().new Builder();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getAdmin() {
        return admin;
    }

    public Car getCar() {
        return car;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    public String getStartString() {
        StringBuilder result = new StringBuilder();
        result
                .append(start.get(Calendar.DATE))
                .append(" ")
                .append(start.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH))
                .append(" ")
                .append(start.get(Calendar.YEAR));
        return result.toString();
    }

    public String getEndString() {
        StringBuilder result = new StringBuilder();
        result
                .append(end.get(Calendar.DATE))
                .append(" ")
                .append(end.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH))
                .append(" ")
                .append(end.get(Calendar.YEAR));
        return result.toString();
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isChildChair() {
        return childChair;
    }

    public void setChildChair(boolean childChair) {
        this.childChair = childChair;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + client.getId() +
                ", admin='" + admin + '\'' +
                ", car=" + car +
                ", start=" + start +
                ", end=" + end +
                ", gps=" + gps +
                ", childChair=" + childChair +
                ", details='" + details + '\'' +
                ", price=" + price +
                '}';
    }
}