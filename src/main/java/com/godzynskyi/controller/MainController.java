package com.godzynskyi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 21.11.2015.
 */
@WebServlet("/page/*")
public class MainController extends HttpServlet {
    RequestHelper requestHelper = RequestHelper.getInstance();

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        processRequest(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        try {
//определение команды, пришедшей из JSP
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            //TODO
        } catch (IOException e) {
            e.printStackTrace();
            //TODO
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/jsp/" + page + ".jsp");
        dispatcher.forward(request, response);
    }

}
