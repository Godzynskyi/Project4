package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.dao.car.filters.CarFilter;
import com.godzynskyi.model.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapper("/admin/repair")
public class CommandRepair implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //Get list of all Cars
        List<Car> carList = DAOFactory.carDAO().findCars(new ArrayList<CarFilter>());

        request.setAttribute("cars", carList);

        return "admin/repair";
    }
}
