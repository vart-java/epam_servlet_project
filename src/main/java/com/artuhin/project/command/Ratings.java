package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ratings implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<List<User>> masters = ServiceFactory.getInstance().getUserService().getAllMastersBySpecilizationSortByRating();
        req.setAttribute("ratings", masters);
        LocalDate localDate = LocalDate.now();
        req.setAttribute("dateNow1", localDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        req.setAttribute("dateNow7", localDate.plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return "pages/ratings.jsp";
    }
}
