package com.artuhin.project.controller;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.notification.trigger.Trigger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private final CommandResolver resolver = CommandResolver.getInstance();

    @Override
    public void init() throws ServletException {
        Trigger.start();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        boolean isLogged = session.getAttribute("user") != null;
        if ("registration".equals(req.getParameter("command")) && !isLogged) {
            req.getRequestDispatcher("pages/registration.jsp").forward(req, resp);
        }
        if ("getRecall".equals(req.getParameter("command")) && !isLogged) {
            User common = new User();
            common.setLogin("guest@yahoo.com");
            common.setRole(Role.GUEST);
            req.getSession().setAttribute("user", common);
            doRequest(req, resp);
        }
        if (!isLogged) {
            req.getRequestDispatcher("pages/authorization.jsp").forward(req, resp);
        } else {
            doRequest(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String page = resolver.getCommand(req).execute(req, resp);
            req.getRequestDispatcher(page).forward(req, resp);
        }
        catch (Exception e){
            req.getRequestDispatcher("pages/404.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        Trigger.stop();
    }
}
