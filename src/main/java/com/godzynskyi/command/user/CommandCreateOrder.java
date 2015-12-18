package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.controller.RequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/create_order")
public class CommandCreateOrder implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String carId = request.getParameter("carId");
        if (carId == null) {
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }

        request.setAttribute("carId", carId);
        return "user/create_order";
    }
}
