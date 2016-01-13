package com.godzynskyi.dao.cache;

import com.godzynskyi.dao.CarDAO;
import com.godzynskyi.model.Car;

public class CarDAOWithCache extends CarDAO {
    @Override
    public Car getCar(int id) {
        Car res = CarCache.getCar(id);
        if (res == null) {
            res = super.getCar(id);
            if (res != null) CarCache.addCar(res);
        }
        return res;
    }

}
