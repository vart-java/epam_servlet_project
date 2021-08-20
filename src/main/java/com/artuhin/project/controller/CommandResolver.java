package com.artuhin.project.controller;

import com.artuhin.project.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandResolver {

    private static final HashMap<String, ICommand> commands = new HashMap();
    private static CommandResolver instance = new CommandResolver();

    private CommandResolver() {
        commands.put("registration", new Registration());
        commands.put("wrong", new WrongCommand());
        commands.put("procedures", new MyProcedures());
        commands.put("auth", new Authorization());
        commands.put("regToEvent", new RegisterToEvent());
        commands.put("logOut", new LogOut());
        commands.put("main", new Main());
        commands.put("ratings",new Ratings());
    }

    public static CommandResolver getInstance() {
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = commands.get("wrong");
        }
        return command;
    }
}
