package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Appointment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecallHandler implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Appointment appointment = ServiceFactory.getInstance().getAppointmentsService().getById(Long.parseLong(req.getParameter("id")));
        if (appointment.isRated()) {
            return "pages/authorization.jsp";
        }
        if (null != req.getParameter("recall_rating")) {
            req.setAttribute("appointment", appointment);
            if (ServiceFactory.getInstance().getUserService().updateRating(appointment.getMasterLogin(), Integer.parseInt(req.getParameter("recall_rating")))) {
                if (ServiceFactory.getInstance().getAppointmentsService().updateRatedToTrue(appointment.getId())) {
                    return "pages/authorization.jsp";
                }
            }
        }
        return "pages/recall.jsp";
    }
}
