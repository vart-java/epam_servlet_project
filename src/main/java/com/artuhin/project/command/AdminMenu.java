package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AdminMenu implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (!user.getRole().equals(Role.ADMINISTRATOR)) {
            return "pages/authorization.jsp";
        }
        if (null != req.getParameter("role")) {
            if (!ServiceFactory.getInstance().getUserService().updateRole(req.getParameter("login"), Role.valueOf(req.getParameter("role").toUpperCase(Locale.ROOT)))) {
                req.setAttribute("message", "sorry, when update role something went wrong");
            }
        }
        if (null != req.getParameter("specialization")) {
           if(!ServiceFactory.getInstance().getUserService().updateSpecialization(req.getParameter("login"), req.getParameter("specialization"))){
               req.setAttribute("message", "sorry, when update specialization something went wrong");
           }
        }
        List<Procedure> procedureList = ServiceFactory.getInstance().getProcedureService().getAll();
        List<Role> roles = new ArrayList<>();
        for (Role r : Role.values()) {
            roles.add(r);
        }
        List<List<User>> users = ServiceFactory.getInstance().getUserService().getAllSortByRole();
        req.setAttribute("ratings", users);
        req.setAttribute("roles", roles);
        req.setAttribute("procedures", procedureList);
        return "pages/adminmenu.jsp";
    }
}
