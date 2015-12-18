package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.entity.Car;
import com.godzynskyi.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/car")
public class CommandCar implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter("id");
        if (carId == null) {
            request.getRequestDispatcher("/page/catalog").forward(request, response);
            return "user/catalog";
        }
        Car car = DAOFactory.carDAO().getCar(Integer.parseInt(carId));

        request.setAttribute("car", car);
        return "user/car";
    }

}
