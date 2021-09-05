package com.artuhin.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Locale implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loc = req.getParameter("language");
        HttpSession session = req.getSession();
        session.setAttribute("loc", loc);
        if (null == session.getAttribute("user")){
            return "pages/authorization.jsp";
        }
        return "pages/main.jsp";
    }
}
