package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.model.Car;
import com.godzynskyi.model.Client;
import com.godzynskyi.model.Order;
import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Config;
import com.godzynskyi.properties.Message;
import com.godzynskyi.util.CalendarUtil;
import com.godzynskyi.util.ReservedDatesOfCar;
import com.godzynskyi.validation.CreateOrderValidation;
import com.godzynskyi.controller.RequestHelper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created by Java Developer on 06.12.2015.
 */
@RequestMapper("/create_order_handler")
public class CommandCreateOrderHandler implements Command {
    private static final Logger logger = Logger.getLogger(CommandCreateOrderHandler.class);

    static class ReleaseReserveThread extends Thread {
        int carId;
        Order order;
        public ReleaseReserveThread(int carId, Order order) {
            this.carId = carId;
            this.order = order;
        }

        @Override
        public void run() {
            try {
                sleep(10*60*1000);
            } catch (InterruptedException e) {
                logger.error(e);
            }
            ReservedDatesOfCar.removeDatesOfCar(carId, order);
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String idCar = request.getParameter("carId");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        CreateOrderValidation.Result validation = CreateOrderValidation.isValid(idCar, dateFrom, dateTo, phone, email);


        if (validation.NO_DATA) {
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }

        if (!validation.VALID) {
            request.setAttribute("error", validation.error);
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }
        if (!validation.error.equals("")) {
            request.setAttribute("error", validation.error);
        }
        int carId = Integer.parseInt(idCar);
        Calendar fromDate = CalendarUtil.getCalendar(dateFrom);
        Calendar toDate = CalendarUtil.getCalendar(dateTo);
        Order order = new Order();
        order.setCar(new Car(carId));
        order.setStart(fromDate);
        order.setEnd(toDate);

        synchronized (ReservedDatesOfCar.class) {
            if (DAOFactory.orderDAO().isAvailableDateForCar(fromDate, toDate, carId)
                    && ReservedDatesOfCar.isAvailableDate(order)) {

                //Add date to reserve
                ReservedDatesOfCar.addOrderToReserve(carId, order);
                new ReleaseReserveThread(carId, order).start();

            } else {

                //Date is reserved
                request.setAttribute("error", Message.get(Message.DATE_IS_BLOCKED));
                return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
            }
        }


        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String details = request.getParameter("details");
        String gps = request.getParameter("gps");
        String childChair = request.getParameter("child_chair");

        Client client = new Client();
        client.setPhone(phone);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);

        Car car = DAOFactory.carDAO().getCar(carId);

        int daysOfRent = (int) ((toDate.getTimeInMillis()- fromDate.getTimeInMillis()) / (1000*60*60*24));
        int price = car.getPrice() * daysOfRent;
        if (gps != null) price += Integer.parseInt(Config.getProperty(Config.GPS_PRICE));
        if (childChair != null) price += Integer.parseInt(Config.getProperty(Config.CHILD_CHAIR_PRICE));


        order.setAdmin(null);
        order.setCar(car);
        order.setDetails(details);
        order.setGps(gps != null);
        order.setChildChair(childChair != null);
        order.setPrice(price);
        order.setClient(client);


        HttpSession session = request.getSession();
        session.setAttribute("order", order);
        return "user/create_order_confirm";
    }
}

