package com.godzynskyi.command.admin;

import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.model.Order;
import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;
import com.godzynskyi.controller.RequestHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapper("/admin/edit_order")
public class CommandEditOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idOrder = request.getParameter("order_id");

        if (idOrder == null) {
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        int orderId = Integer.parseInt(idOrder);

        Order order = DAOFactory.orderDAO().getOrder(orderId);
        HttpSession session = request.getSession();
        session.setAttribute("orderForEdit", order);

        request.setAttribute("order", order);

        return "admin/edit_order";
    }
}
