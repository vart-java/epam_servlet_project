package com.artuhin.project.command;

import com.artuhin.project.dto.ModelMaker;
import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Status;
import com.artuhin.project.services.AppointmentsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MyProcedures implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        AppointmentsService appointmentsService = ServiceFactory.getInstance().getAppointmentsService();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        req.setAttribute("timestamp", timestamp);

        if (user.getRole().equals(Role.CLIENT)) {
            req.setAttribute("clientDto", ModelMaker.getInstance().getClientAppDtos(
                    user.getId(),
                    ServiceFactory.getInstance().getAppointmentsService().getAll(),
                    ServiceFactory.getInstance().getUserService().getAll(),
                    ServiceFactory.getInstance().getProcedureService().getAll()
            ));
        }
        if (user.getRole().equals(Role.MASTER)) {
            Optional.ofNullable(req.getParameter("finished")).ifPresent(f -> appointmentsService.updateStatus(Long.parseLong(f), Status.CONFIRMED));
            req.setAttribute("todaySchedule", ModelMaker.getInstance().getMasterDailyScheduleDto(
                    user.getId(),
                    LocalDateTime.now().getDayOfYear(),
                    ServiceFactory.getInstance().getAppointmentsService().getAll(),
                    ServiceFactory.getInstance().getProcedureService().getAll(),
                    ServiceFactory.getInstance().getUserService().getAll()
                    ));
            req.setAttribute("allAppointments", ModelMaker.getInstance().getFullAppDtosByMaster(user.getId(),
                    ServiceFactory.getInstance().getAppointmentsService().getAll(),
                    ServiceFactory.getInstance().getUserService().getAll(),
                    ServiceFactory.getInstance().getProcedureService().getAll()
                    ));
            return "pages/masters_procedures.jsp";
        }
        if (user.getRole().equals(Role.ADMIN)) {
            Optional.ofNullable(req.getParameter("delete")).ifPresent(d -> appointmentsService.delete(Long.parseLong(d)));
            Optional.ofNullable(req.getParameter("getPaid")).ifPresent(g -> appointmentsService.updateStatus(Long.parseLong(g), Status.FINISHED));
            Optional.ofNullable(req.getParameter("confirm")).ifPresent(c -> appointmentsService.updateStatus(Long.parseLong(c), Status.DECLARED));
            if (null != req.getParameter("newApp")) {
                long id = Long.parseLong(req.getParameter("id"));
                LocalTime localTime = LocalTime.parse(req.getParameter("newApp"), DateTimeFormatter.ofPattern("H:mm"));
                if (!appointmentsService.updateStartTime(id, localTime)) {
                    req.setAttribute("message", "wrong_time");
                }
            }
            req.setAttribute("tableDto", ModelMaker.getInstance().getFullAppDtos(
                    ServiceFactory.getInstance().getAppointmentsService().getAll(),
                    ServiceFactory.getInstance().getUserService().getAll(),
                    ServiceFactory.getInstance().getProcedureService().getAll()
            ));
            return "pages/admin_procedures.jsp";
        }
        return "pages/client_procedures.jsp";
    }
}
