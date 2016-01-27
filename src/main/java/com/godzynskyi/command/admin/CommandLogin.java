package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/login")
public class CommandLogin implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String redirect = (String) request.getAttribute("redirect");

        if (redirect == null) {
            request.setAttribute("redirect", "/page/admin/orders");
            request.setAttribute("params", new HashMap<String, String>());
        }

        return "admin/login";
    }
}
