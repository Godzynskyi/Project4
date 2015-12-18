package com.godzynskyi.command.user;

import com.godzynskyi.annotation.RequestMapper;
import com.godzynskyi.controller.Command;
import com.godzynskyi.properties.Config;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Java Developer on 03.12.2015.
 */
@RequestMapper("/index")
public class CommandIndex implements Command {
    private static final Logger logger = Logger.getLogger("CommandIndex");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("/index");
        return "index";
    }
}
