package com.godzynskyi.dao.cache;

import com.godzynskyi.model.Car;

import java.util.HashMap;
import java.util.Map;

public class CarCache {
    private static CarCache instance = new CarCache();
    Map<Integer, Car> cachedCars = new HashMap<>();

    private CarCache() {}

    /**
     * return null if not cached or object of Car otherwise.
     * @param carId Id of Car.
     * @return object of Car or null if not cached.
     */
    public static Car getCar(int carId) {
        return instance.cachedCars.get(carId);
    }

    public static void addCar(Car car) {
        synchronized (CarCache.class) {
            instance.cachedCars.put(car.getId(), car);
        }
    }

    public static void changeCar(Car car) {
        addCar(car);
    }
}
