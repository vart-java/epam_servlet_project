package com.artuhin.project.command;

import com.artuhin.project.controller.CommandResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Locale implements ICommand {
    private static final String REDIRECTLINK = "redirectLink";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String loc = req.getParameter("language");
        String referer = req.getHeader("Referer");
        HttpSession session = req.getSession();
        session.setAttribute("loc", loc);
        String redirectLink = String.valueOf(session.getAttribute(REDIRECTLINK));
        if (referer.contains("=")) {
            String substring = referer.substring(referer.lastIndexOf("=") + 1);
            if ("locale".equals(substring)) {
                return CommandResolver.getInstance().getCommand(redirectLink).execute(req, resp);
            }
            session.setAttribute(REDIRECTLINK, substring);
            return CommandResolver.getInstance().getCommand(substring).execute(req, resp);
        }
        session.setAttribute(REDIRECTLINK, "main");
        return CommandResolver.getInstance().getCommand("main").execute(req, resp);
    }
}
