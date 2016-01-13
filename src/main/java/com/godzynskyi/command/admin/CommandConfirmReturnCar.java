package com.godzynskyi.command.admin;

import com.godzynskyi.model.Order;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Message;
import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.controller.RequestHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/confirm_return_car")
public class CommandConfirmReturnCar implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idOrder = request.getParameter("orderId");
        int orderId = Integer.parseInt(idOrder);

        if (DAOFactory.orderDAO().changeOrderStatus(orderId, Order.Status.CAR_WAS_RETURNED)) {
            request.setAttribute("message", Message.get(Message.SUCCESSFUL));
        } else {
            request.setAttribute("error", Message.get(Message.SQL_EXCEPTION));
        }

        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}
