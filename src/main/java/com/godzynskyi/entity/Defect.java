package com.godzynskyi.entity;

import java.util.Date;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class Defect {

    int carId;
    int clientId;
    String description;
    float priceForClient;
    Date date = new Date();

    private Defect() {}

    public int getCarId() {
        return carId;
    }

    public int getClientId() {
        return clientId;
    }

    public String getDescription() {
        return description;
    }

    public float getPriceForClient() {
        return priceForClient;
    }

    public Date getDate() {
        return date;
    }

    public static Builder getBuilder() {
        return new Defect().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setcarId(int carId) {
            Defect.this.carId = carId;
            return this;
        }

        public Builder setClientId(int clientId) {
            Defect.this.clientId = clientId;
            return this;
        }

        public Builder setDescription(String description) {
            Defect.this.description = description;
            return this;
        }

        public Builder setPriceForClient(float priceForClient) {
            Defect.this.priceForClient = priceForClient;
            return this;
        }

        public Builder setDate(Date date) {
            Defect.this.date = date;
            return this;
        }

        public Defect build() {
            return Defect.this;
        }
    }

    @Override
    public String toString() {
        return "Defect{" +
                "carId=" + carId +
                ", clientId=" + clientId +
                ", description='" + description + '\'' +
                ", priceForClient=" + priceForClient +
                ", date=" + date +
                '}';
    }
}
