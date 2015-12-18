package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.controller.RequestHelper;
import com.godzynskyi.entity.Car;
import com.godzynskyi.entity.Client;
import com.godzynskyi.entity.Order;
import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.util.CalendarUtil;
import com.godzynskyi.validation.CreateOrderValidation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 06.12.2015.
 */
@RequestMapper("/create_order_handler")
public class CommandCreateOrderHandler implements Command {
    private static final Logger logger = Logger.getLogger("CommandCreateOrderHandler");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String carId = request.getParameter("carId");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        CreateOrderValidation.Result validation = CreateOrderValidation.isValid(carId, dateFrom, dateTo, phone, email);


        if (validation.noData) {
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }

        if (!validation.valid) {
            request.setAttribute("error", validation.error);
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }
        if (!validation.error.equals("")) {
            request.setAttribute("error", validation.error);
        }

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String details = request.getParameter("details");
        String gps = request.getParameter("gps");
        String childChair = request.getParameter("child_chair");


        Order.Builder orderBuilder = Order.newBuilder();
        Client client = new Client();
        client.setPhone(phone);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);

        int clientId = DAOFactory.clientDAO().addClient(client);

        Car car = DAOFactory.carDAO().getCar(Integer.parseInt(carId));


        //URA
        Order order = orderBuilder
                .setAdmin(null)
                .setCar(car)
                .setClient(client)
                .setStart(CalendarUtil.getCalendar(dateFrom))
                .setEnd(CalendarUtil.getCalendar(dateTo))
                .setDetails(details)
                .setGps(gps != null)
                .setChildChair(childChair != null)
                .build();

        int orderId = DAOFactory.orderDAO().addOrder(order);


        return "user/catalog";
    }
}
