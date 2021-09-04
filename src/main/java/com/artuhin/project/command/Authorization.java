package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.User;
import com.artuhin.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Authorization implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.getByLogin(login);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("loc", req.getParameter("language"));
            return "pages/main.jsp";
        }
        req.setAttribute("message", "invalid username or password");
        return "pages/authorization.jsp";
    }

    private String getCryptPassword (String password){
        byte[] bytes = new byte[32];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder pasBuilder = new StringBuilder();
        for (byte b: bytes){
            pasBuilder.append(String.format("%02X", b));
        }
        return pasBuilder.toString();
    }
}
