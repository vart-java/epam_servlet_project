package com.artuhin.project.command;

import com.artuhin.project.dto.ModelMaker;
import com.artuhin.project.dto.models.RecallModel;
import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.enums.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecallHandler implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        RecallModel recallModel = ModelMaker.getInstance().getRecallDto(
                ServiceFactory.getInstance().getAppointmentsService().getById(Long.parseLong(req.getParameter("id"))
                ), ServiceFactory.getInstance().getUserService().getAll(), ServiceFactory.getInstance().getProcedureService().getAll());
        if (!recallModel.getStatus().equals(Status.PAID)) {
            return "pages/authorization.jsp";
        }
        if (null != req.getParameter("recall_rating")) {
            if (ServiceFactory.getInstance().getUserService().updateRating(recallModel.getMasterId(), Integer.parseInt(req.getParameter("recall_rating")))) {
                ServiceFactory.getInstance().getAppointmentsService().updateStatus(Long.parseLong(req.getParameter("id")), recallModel.getStatus());
                return "pages/authorization.jsp";
            }
        }
        req.setAttribute("recallDto", recallModel);
        return "pages/recall.jsp";
    }
}
