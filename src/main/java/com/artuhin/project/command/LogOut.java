package com.artuhin.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", null);
        return "pages/authorization.jsp";
    }
}
