package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.model.Car;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Message;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/create_car")
public class CommandCreateCar implements Command {

    private static final Logger logger = Logger.getLogger(CommandCreateCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("/page/admin/create_car");

        String model = request.getParameter("model");
        String year = request.getParameter("year");
        String color = request.getParameter("color");
        String engine = request.getParameter("engine");
        if (engine != null) engine.replace(',','.');
        String expenditure = request.getParameter("expenditure");
        if (expenditure != null) expenditure.replace(',','.');
        String automatic = request.getParameter("automat");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        if (model == null || year == null || color == null
                || engine == null || expenditure == null || price == null) {
//            request.setAttribute("error", Message.get(Message.CAR_NOT_ADDED));
            return "admin/create_car";
        }

        Car.Transmission transmission = (automatic != null) ? Car.Transmission.AUTOMAT :
                Car.Transmission.MANUAL;

        int yearInt = 0;
        float engineFloat = 0;
        float expenditureFloat = 0;
        int priceInt = 0;

        try {
            yearInt = Integer.parseInt(year);
            engineFloat = Float.parseFloat(engine);
            expenditureFloat = Float.parseFloat(expenditure);
            priceInt = Integer.parseInt(price);

        } catch (Exception e) {
            logger.error(e);
            return "admin/create_car";
        }
        Car car = Car.newBuilder()
                .setModel(model)
                .setYear(yearInt)
                .setColor(color)
                .setEngine(engineFloat)
                .setExpenditure(expenditureFloat)
                .setPrice(priceInt)
                .setTransmission(transmission)
                .setDescription(description)
                .build();

        logger.debug("New car: " + car);

        int carId = DAOFactory.carDAO().addCar(car);
        logger.debug("carId: " + carId);

        if(carId != -1) {
            request.setAttribute("message", Message.get(Message.CAR_WAS_ADDED));
        } else {
            request.setAttribute("error", Message.get(Message.CAR_NOT_ADDED));
        }
        return "admin/create_car";
    }

}
