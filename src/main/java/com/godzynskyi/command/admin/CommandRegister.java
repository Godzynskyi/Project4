package com.godzynskyi.command.admin;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.daoOld.AdminDAO;
import com.godzynskyi.entity.Admin;
import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.properties.Message;
import com.godzynskyi.validation.CreateAdminValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/admin/register")
public class CommandRegister implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter("login");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        synchronized (AdminDAO.class) {
            CreateAdminValidation.Result result = CreateAdminValidation.isValid(login, password1, password2, firstName, lastName);
            if (!result.noData) {
                if (result.valid) {
                    Admin admin = new Admin();
                    admin.setLogin(login);
                    admin.setPassword(password1);
                    admin.setFirstName(firstName);
                    admin.setLastName(lastName);
                    boolean isAdded = DAOFactory.adminDAO().addAdmin(admin);
                    if (isAdded) {
                        String message = Message.getInstance().getProperty(Message.ADMIN_SUCCESSFULLY_ADDED);
                        request.setAttribute("message", message);
                    } else {
                        String error = Message.getInstance().getProperty(Message.SQL_EXCEPTION);
                        request.setAttribute("error", error);
                    }
                } else {
                    request.setAttribute("error", result.error);
                }
            }
        }

        return "admin/register";
    }
}
