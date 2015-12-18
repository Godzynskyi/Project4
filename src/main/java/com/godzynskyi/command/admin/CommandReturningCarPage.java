package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapper("/admin/return_car")
public class CommandReturningCarPage implements Command {

    private static final Logger logger = Logger.getLogger(CommandReturningCarPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idOrderStr = request.getParameter("idOrder");
        logger.debug("idOrderStr: " + idOrderStr);
        int idOrder = Integer.valueOf(idOrderStr);

        Order order = DAOFactory.orderDAO().getOrder(idOrder);

        logger.debug("Order: " + order);
        logger.debug("Client: " + order.getClient());
        request.setAttribute("order", order);
        return "/admin/return_car";
    }
}
