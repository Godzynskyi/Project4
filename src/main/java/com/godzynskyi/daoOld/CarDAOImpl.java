package com.godzynskyi.daoOld;

import com.godzynskyi.daoOld.car.filters.CarFilter;
import com.godzynskyi.entity.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Created by Java Developer on 21.11.2015.
 */
public class CarDAOImpl implements CarDAO {
    private static final String getCarByIdQuery = "Select car.model, car.year, car.color, car.engine, car.expenditure, car.automat, car_price.price0, car_price.price1, car_price.price2, car_price.price3 from car INNER JOIN car_price ON car.id = car_price.car_id WHERE car.id = ?";
    private static final String addCarQuery = "INSERT Into car (model, year, color, engine, expenditure, automat) VALUES (?, ?, ?, ?, ?, ?)";
    private static final Logger logger = Logger.getLogger("CarDAOImpl");

    Connection connection;

    public CarDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Car findCar(int id) {
//        Car res = null;
//        PreparedStatement statement = null;
//        try {
//            statement = connection.prepareStatement(getCarByIdQuery);
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                ModelService modelService = ServiceFactory.modelService();
//                Model model = modelService.getModel(resultSet.getInt(1));
//                res = new Car();
//                res.setModel(model);
//                res.setId(id);
//                res.setYear(resultSet.getInt(2));
//                res.setColor(resultSet.getString(3));
//                res.setEngine(resultSet.getFloat(4));
//                res.setExpenditure(resultSet.getFloat(5));
//                res.setTransmission((resultSet.getBoolean(6))? Car.Transmission.AUTOMAT : Car.Transmission.MANUAL);
//                int[] pr = new int[4];
//                pr[0] = resultSet.getInt(7);
//                pr[1] = resultSet.getInt(8);
//                pr[2] = resultSet.getInt(9);
//                pr[3] = resultSet.getInt(10);
//                res.setPrice(pr[0], pr[1], pr[2], pr[3]);
//                OptionDAO optionDAO = DAOFactory.optionDAO(connection);
//                res.setOptions(optionDAO.getOptionsOfCar(id));
//                return res;
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//        } finally {
//            if (statement != null) try {
//                statement.close();
//            } catch (SQLException e) {
//                logger.error(e);
//            }
//        }
        return null;
    }

    @Override
    public List<Car> findCars(CarFilter filter1, CarFilter... filters) {
//        StringBuilder s = new StringBuilder();
//        s.append("Select car.model, car.year, car.color, car.engine, car.expenditure, car.automat, car_price.price0, car_price.price1, car_price.price2, car_price.price3, car.id ")
//                .append("from car ")
//                .append("inner join model on car.model = model.id ")
//                .append("inner join model_class on model_class.model_id = model.id ")
//                .append("inner join classes on model_class.class_id = classes.id ")
//                .append("inner join car_price on car_price.car_id = car.id ")
//                .append("inner join brand on brand.id = model.brand ")
//                .append("where ")
//                .append(filter1.stringPattern());
//        for (CarFilter f: filters) {
//            s.append(" and ")
//                    .append(f.stringPattern());
//        }
////        s.append(";");
//        Statement statement = null;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(s.toString());
//            List<Car> cars = new ArrayList<Car>();
//            while (resultSet.next()) {
//                Car car = new Car();
//                ModelService modelService = ServiceFactory.modelService();
//                Model model = modelService.getModel(resultSet.getInt(1));
//                car.setModel(model);
//                car.setYear(resultSet.getInt(2));
//                car.setColor(resultSet.getString(3));
//                car.setEngine(resultSet.getFloat(4));
//                car.setExpenditure(resultSet.getFloat(5));
//                car.setTransmission((resultSet.getBoolean(6)) ? Car.Transmission.AUTOMAT : Car.Transmission.MANUAL);
//                int[] pr = new int[4];
//                pr[0] = resultSet.getInt(7);
//                pr[1] = resultSet.getInt(8);
//                pr[2] = resultSet.getInt(9);
//                pr[3] = resultSet.getInt(10);
//                car.setPrice(pr[0], pr[1], pr[2], pr[3]);
//                car.setId(resultSet.getInt(11));
//                OptionDAO optionDAO = DAOFactory.optionDAO(connection);
//                car.setOptions(optionDAO.getOptionsOfCar(resultSet.getInt(11)));
//                cars.add(car);
//            }
//            if (cars.size() != 0) return cars;
//        } catch (SQLException e) {
//            logger.error(e);
//        } finally {
//            if (statement != null) try {
//                statement.close();
//            } catch (SQLException e) {
//                logger.error(e);
//            }
//        }
        return null;
    }

    @Override
    public int addCar(Car car) {
//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement(addCarQuery, Statement.RETURN_GENERATED_KEYS);
//            ModelService modelService = ServiceFactory.modelService();
//            int modelId = modelService.getModelId(car.getModel());
//            logger.debug("modelId: " + modelId);
//            ps.setInt(1, modelId);
//            ps.setInt(2, car.getYear());
//            ps.setString(3, car.getColor());
//            ps.setFloat(4, car.getEngine());
//            ps.setFloat(5, car.getExpenditure());
//            ps.setBoolean(6, car.isAutomat());
//            int i = ps.executeUpdate();
//            logger.debug("ps.executeUpdate(): " + i);
//            int carId = -1;
//            if (i != 0) {
//                ResultSet generatedKeys = ps.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    carId = generatedKeys.getInt(1);
//                    car.setId(carId);
//                }
//            }
//            String query = "INSERT into car_price (car_id, price0, price1, price2, price3) VALUES (?, ?, ?, ?, ?)";
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, carId);
//            ps.setInt(2, car.getPrice(30));
//            ps.setInt(3, car.getPrice(15));
//            ps.setInt(4, car.getPrice(4));
//            ps.setInt(5, car.getPrice(1));
//            int resultAddingPrices = ps.executeUpdate();
//            logger.debug("Result adding prices: " + resultAddingPrices);
//            List<Car.Option> options = car.getOptions();
//            if (carId != -1) {
//                OptionDAO optionDAO = DAOFactory.optionDAO(connection);
//                for (Car.Option option : options) {
//                    optionDAO.addOptionToCar(carId, option);
//                }
//            }
//            return carId;
//        } catch (SQLException e) {
//            logger.error(e);
//        } finally {
//            if (ps != null) try {
//                ps.close();
//            } catch (SQLException e) {
//                logger.error(e);
//            }
//        }
        return -1;
    }

}
