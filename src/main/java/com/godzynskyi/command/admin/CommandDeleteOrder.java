package com.godzynskyi.command.admin;

import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Message;
import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;
import com.godzynskyi.controller.RequestHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/delete_order")
public class CommandDeleteOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idOrder = request.getParameter("order_id");

        if (idOrder == null) {
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        int orderId = Integer.parseInt(idOrder);
        if (DAOFactory.orderDAO().deleteOrder(orderId)) {
            request.setAttribute("message", Message.get(Message.SUCCESSFUL));
        } else {
            request.setAttribute("error", Message.get(Message.SQL_EXCEPTION));
        }
        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}
