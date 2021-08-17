package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.User;
import com.artuhin.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Ratings implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = ServiceFactory.getUserService();
        List<User> masters = userService.getMastersByRating();
        req.setAttribute("ratings",masters);
        return "pages/ratings.jsp";
    }
}
