package com.godzynskyi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class OccurrenceOrder {
    long clientId;
    String admin;
    long carId;

    Date occurrenceDate = new Date();
    List<Defect> defects = new ArrayList<Defect>();
    int totalPrice = 0;

    public void addDefect(Defect defect) {
        defects.add(defect);
        totalPrice += defect.priceForClient;
    }

    public List<Defect> getDefects() {
        return defects;
    }

    public void setDefects(List<Defect> defects) {
        this.defects = defects;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Date getOccurrenceDate() {
        return occurrenceDate;
    }

    public void setOccurrenceDate(Date occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }
}
