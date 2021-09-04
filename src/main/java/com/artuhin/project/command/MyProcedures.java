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
import java.util.Optional;

public class MyProcedures implements ICommand {
    private static final String APPOINTMENTS = "appointments";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        AppointmentsService appointmentsService = ServiceFactory.getInstance().getAppointmentsService();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        req.setAttribute("timestamp", timestamp);

        if (user.getRole().equals(Role.CLIENT)) {
            req.setAttribute(APPOINTMENTS, appointmentsService.getByClientLogin(user.getLogin()));
        }
        if (user.getRole().equals(Role.MASTER)) {
            Optional.ofNullable(req.getParameter("finished")).ifPresent(f -> appointmentsService.updateFinishedToTrue(Long.parseLong(f)));
            List<Appointment> appointments = appointmentsService.getByMasterLogin(user.getLogin());
            List<Appointment> toDayAppointments = new ArrayList<>();

            for (Appointment a : appointments) {
                if (a.getStartTime().toLocalDateTime().getDayOfYear() == LocalDateTime.now().getDayOfYear() && !a.isFinished()) {
                    toDayAppointments.add(a);
                }
            }
            req.setAttribute(APPOINTMENTS, appointments);
            req.setAttribute("toDayAppointments", toDayAppointments);
            return "pages/mastersprocedures.jsp";
        }
        if (user.getRole().equals(Role.ADMINISTRATOR)) {
            Optional.ofNullable(req.getParameter("delete")).ifPresent(d -> appointmentsService.delete(Long.parseLong(d)));
            Optional.ofNullable(req.getParameter("getPaid")).ifPresent(g -> appointmentsService.updatePaidUpToTrue(Long.parseLong(g)));
            Optional.ofNullable(req.getParameter("confirm")).ifPresent(c -> appointmentsService.updateConfirmToTrue(Long.parseLong(c)));
            if (null != req.getParameter("newApp")) {
                long id = Long.parseLong(req.getParameter("id"));
                LocalTime localTime = LocalTime.parse(req.getParameter("newApp"), DateTimeFormatter.ofPattern("H:mm"));
                if (!appointmentsService.updateStartTime(id, localTime)) {
                    req.setAttribute("message", "wrong_time");
                }
            }
            req.setAttribute(APPOINTMENTS, appointmentsService.getAll());
            return "pages/adminprocedures.jsp";
        }
        return "pages/myprocedures.jsp";
    }
}
