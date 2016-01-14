package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.controller.RequestHelper;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.model.Car;
import com.godzynskyi.model.Defect;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapper("/admin/repair_car")
public class CommandRepairCar implements Command {
    private static final Logger logger = Logger.getLogger(CommandRepairCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String car_idString = request.getParameter("car_id");

        int carId;
        try {
            carId = Integer.parseInt(car_idString);
        } catch (Exception e) {
            logger.error(e);
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        List<Defect> defects = DAOFactory.defectDAO().getNotRepairedDefectsOfCar(carId);
        Car car = DAOFactory.carDAO().getCar(carId);


        request.setAttribute("defects", defects);
        request.setAttribute("car", car);

        return "admin/repair_car";
    }
}