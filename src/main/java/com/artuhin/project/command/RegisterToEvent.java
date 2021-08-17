package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class RegisterToEvent implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int userId = (int) req.getSession().getAttribute("userId");
        int masterId = (int) req.getSession().getAttribute("masterId");
        int serviceId = (int) req.getSession().getAttribute("masterId");
        Timestamp timestamp = (Timestamp) req.getSession().getAttribute("timastamp");

        UserService userService = ServiceFactory.getUserService();
        userService.addUsersToEvent(serviceId, masterId, userId, timestamp);
        return "pages/successfulregister.jsp";
    }
}
