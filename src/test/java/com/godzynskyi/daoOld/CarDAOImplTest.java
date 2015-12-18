package com.godzynskyi.daoOld;

import com.godzynskyi.daoOld.car.filters.AutomatFilter;
import com.godzynskyi.daoOld.car.filters.EngineFilter;
import com.godzynskyi.daoOld.car.filters.KlassFilter;
import com.godzynskyi.daoOld.car.filters.YearFilter;
import com.godzynskyi.entity.Car;
import com.godzynskyi.entity.Klass;
import com.godzynskyi.entity.Model;
import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.factory.UtilFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarDAOImplTest {


    @Test
    public void testFindCars() throws Exception {
        Exception exception = null;
        try {
            CarDAO carDAO = new CarDAOImpl(UtilFactory.getDBConnection());
            carDAO.findCars(new KlassFilter(Klass.EKONOM),
                    new AutomatFilter(Car.Transmission.MANUAL), new EngineFilter(1.2, 2.0), new YearFilter(2006, 2015));
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(null, exception);
    }

    @Test
    public void testAddCar() {
        Exception exception = null;
        try {
            Car car = new Car();
            Model model = new Model("Skoda", "Octavia");
            car.setModel(model);
            car.setYear(2009);
            car.setColor("Metallic");
            car.setEngine(1.6f);
            car.setExpenditure(5.2f);
            car.setTransmission(Car.Transmission.MANUAL);
            car.addOption(Car.Option.CLIMAT_CONTROLE);
            car.addOption(Car.Option.LEATHER);

            CarDAO carDAO = new CarDAOImpl(UtilFactory.getDBConnection());
            carDAO.addCar(car);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(null, exception);
    }

    @Test
    public void testFindCar() throws Exception {
        Exception ex = null;
        try {
            CarDAO carDAO = DAOFactory.carDAO(UtilFactory.getDBConnection());
            Car car = carDAO.findCar(1);
            System.out.println(car.getId());
            System.out.println(car.getModel().getModel());
        } catch (Exception e) {
            ex = e;
        }

        assertEquals(null, ex);
    }
}