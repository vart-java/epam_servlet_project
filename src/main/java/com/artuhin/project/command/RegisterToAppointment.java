package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegisterToAppointment implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateAppointment"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int time = localDateTime.getDayOfYear();

        List<Appointment> appointments = ServiceFactory.getInstance().getAppointmentsService().getByMasterLoginByDay(req.getParameter("master"), time);
        req.setAttribute("appointments", appointments);
        req.setAttribute("time", time);

        User master = ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter("master"));
        req.setAttribute("master", master);
        return "pages/appointmentregistration.jsp";
    }
}
