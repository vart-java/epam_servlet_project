package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.services.AppointmentsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyProcedures implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        AppointmentsService appointmentsService = ServiceFactory.getInstance().getAppointmentsService();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        req.setAttribute("timestamp", timestamp);
        if (user.getRole().equals(Role.CLIENT)){
            req.setAttribute("appointments", appointmentsService.getByClientLogin(user.getLogin()));
        }
        if (user.getRole().equals(Role.MASTER)){
            if (null != req.getParameter("finished")){
                appointmentsService.updateFinishedToTrue(Long.parseLong(req.getParameter("finished")));
            }
            List<Appointment>appointments = appointmentsService.getByMasterLogin(user.getLogin());
            List<Appointment>toDayAppointments = new ArrayList<>();

            for (Appointment a: appointments) {
                if (a.getStartTime().toLocalDateTime().getDayOfYear()==LocalDateTime.now().getDayOfYear() && !a.isFinished()){
                    toDayAppointments.add(a);
                }
            }
            req.setAttribute("appointments", appointments);
            req.setAttribute("toDayAppointments", toDayAppointments);
            return "pages/mastersprocedures.jsp";
        }
        if (user.getRole().equals(Role.ADMINISTRATOR)){
            if (null != req.getParameter("delete")){
                appointmentsService.delete(Long.parseLong(req.getParameter("delete")));
            }
            if (null != req.getParameter("getPaid")){
                appointmentsService.updatePaidUpToTrue(Long.parseLong(req.getParameter("getPaid")));
            }
            if (null != req.getParameter("confirm")){
                appointmentsService.updateConfirmToTrue(Long.parseLong(req.getParameter("confirm")));
            }
            if (null != req.getParameter("newApp")){
                long id = Long.parseLong(req.getParameter("id"));
                LocalTime localTime = LocalTime.parse(req.getParameter("newApp"), DateTimeFormatter.ofPattern("H:mm"));
                if(!appointmentsService.updateStartTime(id, localTime)){
                    req.setAttribute("message", "Sorry, but you chose the wrong time. Please enter the correct time according to the recommendations on this page");
                }
            }
            req.setAttribute("appointments", appointmentsService.getAll());
            return "pages/adminprocedures.jsp";
        }
        return "pages/myprocedures.jsp";
    }
}
