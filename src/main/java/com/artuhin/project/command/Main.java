package com.artuhin.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "pages/main.jsp";
    }
}
