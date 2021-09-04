package com.artuhin.project.controller;

import com.artuhin.project.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandResolver {

    private static final HashMap<String, ICommand> commands = new HashMap();
    private static CommandResolver instance;

    private CommandResolver() {
        commands.put("registration", new Registration());
        commands.put("wrong", new WrongCommand());
        commands.put("procedures", new MyProcedures());
        commands.put("auth", new Authorization());
        commands.put("regToApp", new RegisterToAppointment());
        commands.put("logOut", new LogOut());
        commands.put("main", new Main());
        commands.put("ratings", new Ratings());
        commands.put("regByMasterName", new RegisterByMasterName());
        commands.put("regByProcedure", new RegisterByProcedure());
        commands.put("authAsGuest", new AuthorizationAsGuest());
        commands.put("getRecall", new RecallHandler());
        commands.put("adminMenu", new AdminMenu());
        commands.put("locale", new Locale());
    }

    public static synchronized CommandResolver getInstance() {
        if (instance == null) {
            instance = new CommandResolver();
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = commands.get("wrong");
        }
        return command;
    }

    public ICommand getCommand(String commandA) {
        ICommand command = commands.get(commandA);
        if (command == null) {
            command = commands.get("wrong");
        }
        return command;
    }
}
