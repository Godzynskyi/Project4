package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.entity.Defect;
import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.util.CalendarUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@RequestMapper("/admin/confirm_returning")
public class CommandConfirmReturningCar implements Command {

    private static final Logger logger = Logger.getLogger(CommandConfirmReturningCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String price = request.getParameter("price");
        String carId = request.getParameter("carId");
        String clientId = request.getParameter("clientId");

        boolean existsDefects = request.getParameter("existsDefects") != null;
        logger.debug("Defects are exists: " + existsDefects);
        if(existsDefects) {
            String description = request.getParameter("description");
            String dateString = request.getParameter("date");
            Calendar date = CalendarUtil.getCalendar(dateString);
            Defect defect = Defect
                    .getBuilder()
                    .setcarId(Integer.valueOf(carId))
                    .setClientId(Integer.valueOf(clientId))
                    .setPriceForClient(Float.valueOf(price))
                    .setDescription(description)
                    .setDate(date.getTime())
                    .build();
            logger.debug("Defects: " + defect);

            DAOFactory.defectDAO().addDefect(defect);
        }
        return "user/catalog";
    }
}
