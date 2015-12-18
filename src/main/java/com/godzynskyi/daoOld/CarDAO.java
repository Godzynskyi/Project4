package com.godzynskyi.daoOld;

import com.godzynskyi.daoOld.car.filters.CarFilter;
import com.godzynskyi.entity.Car;

import java.util.List;

/**
 * Created by Java Developer on 21.11.2015.
 */
public interface CarDAO {
    /**
     * Returns car by ID.
     * @param id
     * @return
     */
    Car findCar(int id);

    List<Car> findCars(CarFilter filter1, CarFilter... filters);

    /**
     * Add info to tables car, car_price, car_options.
     *
     * @return true if successfully or false otherwise.
     */
    int addCar(Car car);

}
