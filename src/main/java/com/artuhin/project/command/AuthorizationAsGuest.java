package com.artuhin.project.command;


import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationAsGuest implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User common = new User();
        common.setLogin("guest@yahoo.com");
        common.setRole(Role.GUEST);
        req.getSession().setAttribute("user", common);
        return "pages/main.jsp";
    }
}
