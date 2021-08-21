package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.services.AppointmentsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyProcedures implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        AppointmentsService appointmentsService = ServiceFactory.getEventsService();
        if (user.getRole().equals(Role.CLIENT)){
            req.setAttribute("events", appointmentsService.getByClientLogin(user.getLogin()));
        }
        if (user.getRole().equals(Role.MASTER)){
            req.setAttribute("events", appointmentsService.getByMasterLogin(user.getLogin()));
        }
        return "pages/myprocedures.jsp";
    }
}
