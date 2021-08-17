package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.services.EventsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyEvents implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        EventsService eventsService = ServiceFactory.getEventsService();
        if (user.getRole().equals(Role.CLIENT)){
            req.setAttribute("events", eventsService.getByClientId(user.getId()));
        }
        if (user.getRole().equals(Role.MASTER)){
            req.setAttribute("events", eventsService.getByMasterId(user.getId()));
        }
        return "pages/myevents.jsp";
    }
}
