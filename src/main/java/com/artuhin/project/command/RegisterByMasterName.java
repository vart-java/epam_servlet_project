package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.services.AppointmentsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RegisterByMasterName implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> masters = ServiceFactory.getInstance().getUserService().getAllMastersSortByRating();
        Collections.sort(masters, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getLogin().compareTo(o2.getLogin());
            }
        });
        LocalDate localDate = LocalDate.now();
        req.setAttribute("masters", masters);
        req.setAttribute("dateNow1", localDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        req.setAttribute("dateNow7", localDate.plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "pages/bymastername.jsp";
    }
}

