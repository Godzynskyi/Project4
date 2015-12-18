package com.godzynskyi.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class Car {

    int id;
    String model;
    int year;
    String color;
    float engine; // l
    float expenditure; // l/100km
    Transmission transmission;
    int price;
    private String description;

    public boolean isAutomat() {
        return transmission == Transmission.AUTOMAT;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public float getEngine() {
        return engine;
    }

    public float getExpenditure() {
        return expenditure;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public enum Transmission {
        MANUAL, AUTOMAT;
    }

    public static Builder newBuilder() {
        return new Car().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(int id) {
            Car.this.id = id;
            return this;
        }

        public Builder setModel(String model) {
            Car.this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            Car.this.year = year;
            return this;
        }

        public Builder setColor(String color) {
            Car.this.color = color;
            return this;
        }

        public Builder setEngine(float engine) {
            Car.this.engine = engine;
            return this;
        }

        public Builder setExpenditure(float expenditure) {
            Car.this.expenditure = expenditure;
            return this;
        }

        public Builder setTransmission(Transmission transmission) {
            Car.this.transmission = transmission;
            return this;
        }

        public Builder setPrice(int price) {
            Car.this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            Car.this.description = description;
            return this;
        }

        public Car build() {
            return Car.this;
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model=" + model +
                ", year=" + year +
                ", color='" + color + "'" +
                ", engine=" + engine +
                ", expenditure=" + expenditure +
                ", transmission=" + transmission +
                ", price=" + price +
                '}';
    }
}