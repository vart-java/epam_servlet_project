package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegisterToAppointment implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (null != req.getParameter("newApp")) {
            User user = (User) req.getSession().getAttribute("user");
            LocalDate localDate1 = LocalDate.parse(req.getParameter("day"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            LocalTime localTime = LocalTime.parse(req.getParameter("newApp"), DateTimeFormatter.ofPattern("H:mm"));
            LocalDateTime localDateTime = LocalDateTime.of(localDate1, localTime);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            Appointment appointment = new Appointment(ServiceFactory.getInstance().getProcedureService()
                    .getProcedureByName(ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter("master"))
                            .getSpecialization().getName()), req.getParameter("master"), user.getLogin(), timestamp, false, false, false, false);
            int time = localDate1.getDayOfYear();
            req.setAttribute("time", localDate1.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            User master = ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter("master"));
            req.setAttribute("master", master);
            req.setAttribute("wrongTime", localDate1.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            int id = ServiceFactory.getInstance().getAppointmentsService().create(appointment);
            List<Appointment> appointments = ServiceFactory.getInstance().getAppointmentsService().getByMasterLoginByDay(req.getParameter("master"), time);
            req.setAttribute("appointments", appointments);
            if (id == 0) {
                req.setAttribute("message", "Sorry, but you chose the wrong time. Please enter the correct time according to the recommendations on this page");
                return "pages/appointmentregistration.jsp";
            }
            req.setAttribute("message", "Congratulations, you have successfully made an appointment (id: " + id + ") with the master. In the near future, the administrator will confirm the procedure");
            return "pages/appointmentregistration.jsp";
        }
        LocalDate localDate = LocalDate.parse(req.getParameter("dateAppointment"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int time = localDate.getDayOfYear();
        List<Appointment> appointments = ServiceFactory.getInstance().getAppointmentsService().getByMasterLoginByDay(req.getParameter("master"), time);
        req.setAttribute("appointments", appointments);
        String s = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.setAttribute("time", s);
        User master = ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter("master"));
        req.setAttribute("master", master);
        return "pages/appointmentregistration.jsp";
    }
}
