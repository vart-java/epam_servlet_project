package com.artuhin.project.command;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Registration implements ICommand {

    private static Logger LOGGER = LogManager.getLogger(Registration.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        user.setLogin(req.getParameter("username"));
        user.setPassword(getCryptPassword(req.getParameter("password")));
        user.setRole(Role.GUEST);
        int id = ServiceFactory.getUserService().createUser(user);
        if (id != 0) {
            user.setId(id);
            req.getSession().setAttribute("user", user);
            return "pages/main.jsp";
        }
        req.setAttribute("message", "sorry, current login already taken");
        return "pages/registration.jsp";
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
