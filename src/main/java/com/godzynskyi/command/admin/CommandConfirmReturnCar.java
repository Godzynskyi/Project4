package com.godzynskyi.command.admin;

import com.godzynskyi.model.Defect;
import com.godzynskyi.model.Order;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Message;
import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;
import com.godzynskyi.controller.RequestHelper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapper("/admin/confirm_return_car")
public class CommandConfirmReturnCar implements Command {
    private static final Logger logger = Logger.getLogger(CommandConfirmReturnCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderIdString = request.getParameter("order_id");
        String carIdString = request.getParameter("car_id");

        int orderId;
        int carId;
        try {
            orderId = Integer.parseInt(orderIdString);
            carId = Integer.parseInt(carIdString);
        } catch (NumberFormatException e) {
            logger.error(e);
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        List<Defect> clientsDefects = DAOFactory.defectDAO().getNotPaidDefectsOfCar(carId);
        if (clientsDefects.size() != 0) {
            request.setAttribute("defects", clientsDefects);
            return "admin/pay_defects";
        }


        if (DAOFactory.orderDAO().changeOrderStatus(orderId, Order.Status.CAR_WAS_RETURNED)) {
            request.setAttribute("message", Message.get(Message.SUCCESSFUL));
        } else {
            request.setAttribute("error", Message.get(Message.SQL_EXCEPTION));
        }

        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}
