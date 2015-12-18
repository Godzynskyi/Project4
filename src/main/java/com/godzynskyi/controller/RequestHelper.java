package com.godzynskyi.controller;

import com.godzynskyi.properties.Config;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Java Developer on 03.12.2015.
 */
public class RequestHelper {
    private static final Logger logger = Logger.getLogger("ControllerHelper");

    private static RequestHelper instance = new RequestHelper();
    private Map<String, Command> commands;


    private RequestHelper() {
        String pack = Config.getInstance().getProperty(Config.CONTROLLERS_PACKAGE);
        String pref = Config.getInstance().getProperty(Config.SERVLET_PREFIX);
        commands = new AnnotationCommands().putCommandsToMap(pack,pref);
        logger.debug("Commands: " + commands);
    }

    public Command getCommand(String address) {
        logger.debug(address);
        Command command = commands.get(address);
        if (command == null) {
            return commands.get("/page/catalog");
        }
        return command;
    }

    public Command getCommand(HttpServletRequest request) {
        return getCommand(request.getRequestURI());
    }

    public static RequestHelper getInstance() {
        return instance;
    }
}
