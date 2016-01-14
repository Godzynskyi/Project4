package com.godzynskyi.filter;

import com.godzynskyi.dao.DAOFactory;
import com.godzynskyi.properties.Config;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Java Developer on 05.12.2015.
 */
public class AdminSecurityFilter implements Filter {
    private static final Logger logger = Logger.getLogger("AdminSecurityFilter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        boolean isAdmin = isAdmin(httpRequest);
        logger.debug("isAdmin - " + isAdmin);


        if (!isAdmin) {
            String LOGIN_PAGE = Config.getProperty(Config.LOGIN_PAGE);
            logger.debug("Redirect to login page.");

            request.setAttribute("redirect", httpRequest.getRequestURI());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }


    boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        String admin = (String) session.getAttribute("admin");
        if (admin == null) {
            String username = request.getParameter("username");
            if (username == null) return false;

            String password = request.getParameter("password");
            if (password == null) return false;

            logger.debug("Username: " + username + ", password: " + password);

            if (DAOFactory.adminDAO().isAdmin(username, password)) {
                session.setAttribute("admin", username);
            } else {
                return false;
            }
        }

        return true;
    }
}
