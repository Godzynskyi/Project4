package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.entity.Car;
import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.properties.Message;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapper("/admin/create_car")
public class CommandNewCar implements Command {

    private static final Logger logger = Logger.getLogger(CommandNewCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug("/page/admin/create_car");

        String model = request.getParameter("model");
        String year = request.getParameter("year");
        String color = request.getParameter("color");
        String engine = request.getParameter("engine");
        String expenditure = request.getParameter("expenditure");
        String automatic = request.getParameter("automat");
        String price = request.getParameter("price");

        if (model == null || year == null || color == null
                || engine == null || expenditure == null || price == null) {
            request.setAttribute("error", Message.getInstance().getProperty(Message.CAR_NOT_ADDED));
            return "admin/new_car_page";
        }

        Car.Transmission transmission = (automatic != null) ? Car.Transmission.AUTOMAT :
                Car.Transmission.MANUAL;

        Car car = Car.newBuilder()
                .setModel(model)
                .setYear(Integer.valueOf(year))
                .setColor(color)
                .setEngine(Float.valueOf(engine))
                .setExpenditure(Float.valueOf(expenditure))
                .setPrice(Integer.parseInt(price))
                .setTransmission(transmission)
                .build();

        logger.debug("New car: " + car);

        int carId = DAOFactory.carDAO().addCar(car);
        logger.debug("carId: " + carId);

        if(carId == 1) {
            request.setAttribute("message", Message.getInstance().getProperty(Message.CAR_WAS_ADDED));
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.CAR_NOT_ADDED));
        }
        return "admin/new_car_page";
    }

}
