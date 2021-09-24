package com.artuhin.project.command;

import com.artuhin.project.dto.ModelMaker;
import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MasterRatings implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (!user.getRole().equals(Role.CLIENT)) {
            return "pages/main.jsp";
        }

        req.setAttribute("ratings", ModelMaker.getInstance().getRateTableDtos(
                ServiceFactory.getInstance().getProcedureService().getAll(),
                ServiceFactory.getInstance().getUserService().getAll()
        ));
        LocalDate localDate = LocalDate.now();
        req.setAttribute("dateNow1", localDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        req.setAttribute("dateNow7", localDate.plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "pages/master_ratings.jsp";
    }
}
