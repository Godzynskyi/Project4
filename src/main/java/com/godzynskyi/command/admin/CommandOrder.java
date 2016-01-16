package com.godzynskyi.command.admin;

import com.godzynskyi.model.Order;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;
import com.godzynskyi.controller.RequestHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/order")
public class CommandOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String idOrder = request.getParameter("orderId");

        if (idOrder == null) {
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        int orderId = Integer.parseInt(idOrder);

        Order order = DAOFactory.orderDAO().getOrder(orderId);

        request.setAttribute("order", order);

        return "admin/order";
    }
}
