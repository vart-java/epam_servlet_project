package com.artuhin.project.command;

import com.artuhin.project.dto.ModelMaker;
import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.enums.Status;
import com.artuhin.project.services.ProcedureService;
import com.artuhin.project.services.servicesimpl.ProcedureServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RegisterToAppointment implements ICommand {
    private static final String DATEPATTERN = "dd.MM.yyyy";
    private static final String MASTER = "masterId";
    private static final String PAGE = "pages/appointment_registration.jsp";
    private ProcedureService procedureService = ProcedureServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (!user.getRole().equals(Role.CLIENT)) {
            return "pages/main.jsp";
        }
        if (null != req.getParameter("newApp")) {
            LocalDate localDate1 = LocalDate.parse(req.getParameter("day"), DateTimeFormatter.ofPattern(DATEPATTERN));
            LocalTime localTime = LocalTime.parse(req.getParameter("newApp"), DateTimeFormatter.ofPattern("H:mm"));
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(localDate1, localTime));
            Appointment appointment = new Appointment()
                    .builder()
                    .procedureId(Long.parseLong(req.getParameter("procedureId")))
                    .masterId(Long.parseLong(req.getParameter(MASTER)))
                    .clientId(user.getId())
                    .startTime(timestamp)
                    .status(Status.DECLARED)
                    .build();
            int time = localDate1.getDayOfYear();
            req.setAttribute("time", localDate1.format(DateTimeFormatter.ofPattern(DATEPATTERN)));
            req.setAttribute("procedure", procedureService.getProcedureById(Long.parseLong(req.getParameter("procedureId"))));
            req.setAttribute("master", ModelMaker.getInstance().getMasterDto(ServiceFactory.getInstance().getUserService().getById(Long.parseLong(req.getParameter(MASTER)))));
            req.setAttribute("wrongTime", localDate1.format(DateTimeFormatter.ofPattern(DATEPATTERN)));
            long id = ServiceFactory.getInstance().getAppointmentsService().create(appointment).getId();
            req.setAttribute("masterApp", ModelMaker.getInstance().getMasterDailyScheduleDto(
                    Long.parseLong(req.getParameter(MASTER)), time,
                    ServiceFactory.getInstance().getAppointmentsService().getAll(),
                    ServiceFactory.getInstance().getProcedureService().getAll(),
                    ServiceFactory.getInstance().getUserService().getAll()
            ));
            if (id == -1) {
                req.setAttribute("message", "wrong_time");
            } else {
                req.setAttribute("message", "successful_reg");
            }
        } else {
            LocalDate localDate = LocalDate.parse(req.getParameter("dateAppointment"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int time = localDate.getDayOfYear();
            String s = localDate.format(DateTimeFormatter.ofPattern(DATEPATTERN));
            req.setAttribute("time", s);
            req.setAttribute("procedure", procedureService.getProcedureById(Long.parseLong(req.getParameter("procedureId"))));
            req.setAttribute("master", ModelMaker.getInstance().getMasterDto(ServiceFactory.getInstance().getUserService().getById(Long.parseLong(req.getParameter(MASTER)))));
            req.setAttribute("masterApp", ModelMaker.getInstance().getMasterDailyScheduleDto(
                    Long.parseLong(req.getParameter(MASTER)), time,
                    ServiceFactory.getInstance().getAppointmentsService().getAll(),
                    ServiceFactory.getInstance().getProcedureService().getAll(),
                    ServiceFactory.getInstance().getUserService().getAll()));
        }
        return PAGE;
    }
}
