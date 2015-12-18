package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/login")
public class CommandLogin implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = (String) request.getAttribute("redirect");
        if (redirect == null) request.setAttribute("redirect", "/page/admin/orders");


        return "admin/login";
    }
}
