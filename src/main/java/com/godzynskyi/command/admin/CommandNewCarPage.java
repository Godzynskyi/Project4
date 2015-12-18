package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.entity.Car;
import com.godzynskyi.entity.Klass;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequestMapper("/admin/new_car_page")
public class CommandNewCarPage implements Command {

    private static final Logger logger = Logger.getLogger(CommandNewCarPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug("/admin/new_car_page");

        return "admin/new_car_page";
    }
}
