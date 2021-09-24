package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.services.UserService;
import com.artuhin.project.util.FieldChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authorization implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        if (!FieldChecker.checkEmail(login) && !FieldChecker.checkPassword(password)) {
            req.setAttribute("message", "invalid_login");
            return "pages/authorization.jsp";
        }
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.getAll().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
        if (user != null && getCryptPassword(password).equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
            if (null == req.getSession().getAttribute("loc")) {
                req.getSession().setAttribute("loc", "en");
            }
            return "pages/main.jsp";
        }
        req.setAttribute("message", "invalid_login");
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
