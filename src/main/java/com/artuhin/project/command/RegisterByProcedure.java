package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterByProcedure implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> mastersAll = ServiceFactory.getInstance().getUserService().getAllMastersSortByRating();
        List<Procedure> procedureList = ServiceFactory.getInstance().getProcedureService().getAll();
        List<User> topMasters = new ArrayList<>();
        for (Procedure p : procedureList) {
            for (User user : mastersAll) {
                if (user.getSpecialization().getName().equals(p.getName())) {
                    topMasters.add(user);
                    break;
                }
            }
        }
        LocalDate localDate = LocalDate.now();
        req.setAttribute("masters", topMasters);
        req.setAttribute("dateNow1", localDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        req.setAttribute("dateNow7", localDate.plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "pages/byprocedure.jsp";
    }
}
