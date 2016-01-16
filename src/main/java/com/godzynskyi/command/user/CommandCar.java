package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;
import com.godzynskyi.model.Car;
import com.godzynskyi.model.Order;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.util.ReservedDatesOfCar;
import com.godzynskyi.controller.RequestHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/car")
public class CommandCar implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String carId = request.getParameter("id");
        if (carId == null) {
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }
        int idCar = Integer.parseInt(carId);
        Car car = DAOFactory.carDAO().getCar(idCar);
        Set<Order> reservedDates = DAOFactory.orderDAO().getBlockedDates(idCar);
        reservedDates.addAll(ReservedDatesOfCar.getDatesOfCar(idCar));

        request.setAttribute("reservedDates", reservedDates);
        request.setAttribute("car", car);
        return "user/car";
    }

}
