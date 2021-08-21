package com.artuhin.project.util.rsparser;

import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WithoutReflectionParser {
    private static Logger logger = LogManager.getLogger(WithoutReflectionParser.class);
    private static WithoutReflectionParser instance = new WithoutReflectionParser();

    private WithoutReflectionParser() {
    }

    public static WithoutReflectionParser getInstance() {
        return instance;
    }

    public static List<User> userParser(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role_name")));
                if (resultSet.getInt("rating") != 0) {
                    user.setRating(resultSet.getInt("rating"));
                }
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQL exception from parser", e);
        }
        return userList;
    }
}

