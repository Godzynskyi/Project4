package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;
import com.godzynskyi.controller.RequestHelper;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Message;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/pay_defects_handler")
public class CommandPayDefectsHandler implements Command {
    private static final Logger logger = Logger.getLogger(CommandPayDefectsHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String order_id = request.getParameter("order_id");
        String submit = request.getParameter("submit");

        int orderId;
        try {
            orderId = Integer.parseInt(order_id);
        } catch (Exception e) {
            logger.error(e);
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        if ("Cancel".equals(submit)) {
            return RequestHelper.getInstance().getCommand("/page/admin/change_order_status").execute(request, response);
        }

        if ("Submit".equals(submit)) {
            if (DAOFactory.defectDAO().paidForOrdersDefects(orderId)) {
                request.setAttribute("message", Message.get(Message.SUCCESSFUL));
            } else {
                request.setAttribute("error", Message.get(Message.SQL_EXCEPTION));
            }
        }


        return RequestHelper.getInstance().getCommand("/page/admin/confirm_return_car").execute(request, response);
    }
}
