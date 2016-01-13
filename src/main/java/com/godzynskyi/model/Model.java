package com.godzynskyi.model;

/**
 * Created by Java Developer on 23.11.2015.
 */
public class Model {
    String brand;
    String model;
    String about;

    public Model(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public String toString() {
        return "Model{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
