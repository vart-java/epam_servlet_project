package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.services.UserService;
import com.artuhin.project.util.FieldChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Registration implements ICommand {
    UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!FieldChecker.checkEmail(req.getParameter("username")) && !FieldChecker.checkPassword(req.getParameter("password"))) {
            req.setAttribute("message", "invalid_login");
            return "pages/authorization.jsp";
        }

        User user = new User().builder().login(req.getParameter("username"))
                .password(getCryptPassword(req.getParameter("password")))
                .role(Role.CLIENT).build();
        if (userService.createUser(user).getId()>0) {
            req.getSession().setAttribute("user", user);
            return "pages/main.jsp";
        }
        req.setAttribute("message", "current_long_no");
        return "pages/authorization.jsp";
    }

    private String getCryptPassword(String password) {
        byte[] bytes = new byte[32];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder pasBuilder = new StringBuilder();
        for (byte b : bytes) {
            pasBuilder.append(String.format("%02X", b));
        }
        return pasBuilder.toString();
    }
}
