package com.godzynskyi.controller;

import com.godzynskyi.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 21.11.2015.
 *
 * This is a Main Servlet that handle all requests
 * and delegate requests to particular Command Class.
 */
public class MainController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainController.class);
    RequestHelper requestHelper = RequestHelper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        //Getting particular Command Object, from Request
        Command command = requestHelper.getCommand(request);
        page = command.execute(request, response);

        //Throw request and response objects to jsp page (MVC pattern)
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/jsp/" + page + ".jsp");
        dispatcher.forward(request, response);
    }

}
