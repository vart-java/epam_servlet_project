package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.AppointmentBuilder;
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
    private static final String DATEPATTERN = "dd.MM.yyyy";
    private static final String MASTER = "master";
    private static final String PAGE = "pages/appointmentregistration.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (null != req.getParameter("newApp")) {
            User user = (User) req.getSession().getAttribute("user");
            LocalDate localDate1 = LocalDate.parse(req.getParameter("day"), DateTimeFormatter.ofPattern(DATEPATTERN));
            LocalTime localTime = LocalTime.parse(req.getParameter("newApp"), DateTimeFormatter.ofPattern("H:mm"));
            LocalDateTime localDateTime = LocalDateTime.of(localDate1, localTime);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            Appointment appointment = AppointmentBuilder.getInstance().setProcedure(ServiceFactory.getInstance().getProcedureService()
                            .getProcedureByName(ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter(MASTER))
                                    .getSpecialization().getName())).setMasterLogin(req.getParameter(MASTER)).setClientLogin(user.getLogin())
                    .setStartTime(timestamp).setConfirmed(false).setPaidUp(false).setFinished(false).setRated(false).getAppointment();
            int time = localDate1.getDayOfYear();
            req.setAttribute("time", localDate1.format(DateTimeFormatter.ofPattern(DATEPATTERN)));
            User master = ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter(MASTER));
            req.setAttribute(MASTER, master);
            req.setAttribute("wrongTime", localDate1.format(DateTimeFormatter.ofPattern(DATEPATTERN)));
            int id = ServiceFactory.getInstance().getAppointmentsService().create(appointment);
            List<Appointment> appointments = ServiceFactory.getInstance().getAppointmentsService().getByMasterLoginByDay(req.getParameter(MASTER), time);
            req.setAttribute("appointments", appointments);
            if (id == 0) {
                req.setAttribute("message", "wrong_time");
            } else {
                req.setAttribute("message", "successful_reg");
            }
        } else {
            LocalDate localDate = LocalDate.parse(req.getParameter("dateAppointment"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int time = localDate.getDayOfYear();
            List<Appointment> appointments = ServiceFactory.getInstance().getAppointmentsService().getByMasterLoginByDay(req.getParameter(MASTER), time);
            req.setAttribute("appointments", appointments);
            String s = localDate.format(DateTimeFormatter.ofPattern(DATEPATTERN));
            req.setAttribute("time", s);
            User master = ServiceFactory.getInstance().getUserService().getByLogin(req.getParameter(MASTER));
            req.setAttribute(MASTER, master);
        }
        return PAGE;
    }
}
