package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.daoOld.order.filters.AdminFilter;
import com.godzynskyi.daoOld.order.filters.CarFilter;
import com.godzynskyi.daoOld.order.filters.NoAdminFilter;
import com.godzynskyi.daoOld.order.filters.OrderFilter;
import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/admin/orders")
public class CommandOrders implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderFilter> orderFilters = new ArrayList<>();

        // Add Admin Filter
        String admin = request.getParameter("admin");
        if (admin == null) admin = (String) request.getSession().getAttribute("admin");
        OrderFilter orderFilter = null;
        if (!admin.equals("null")) orderFilter = new AdminFilter(admin);
        else orderFilter = new NoAdminFilter();
        orderFilters.add(orderFilter);

        // Add Car Filter
        String carId = request.getParameter("car");
        if (carId != null) {
            OrderFilter orderFilter1 = new CarFilter(Integer.parseInt(carId));
            orderFilters.add(orderFilter1);
        }

        List<Order> orderList = DAOFactory.orderDAO().getOrdersWithoutClientAndCar(orderFilters);

        request.setAttribute("orderList", orderList);
        request.setAttribute("admin", admin);
        return "admin/orders";
    }
}
