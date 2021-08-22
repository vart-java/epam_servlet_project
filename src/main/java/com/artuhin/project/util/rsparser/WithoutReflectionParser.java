package com.artuhin.project.util.rsparser;

import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WithoutReflectionParser {
    private static Logger logger = LogManager.getLogger(WithoutReflectionParser.class);
    private static WithoutReflectionParser instance = new WithoutReflectionParser();

    private WithoutReflectionParser() {
    }

    public static WithoutReflectionParser getInstance() {
        return instance;
    }

    public List<User> usersParser(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role_name").toUpperCase(Locale.ROOT)));
                if (resultSet.getInt("rating") != 0) {
                    user.setRating(resultSet.getInt("rating"));
                }
                if (resultSet.getString("specialization") != null) {
                    user.setSpecialization(new Procedure(resultSet.getString("specialization")));
                }
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQL exception from parser", e);
        }
        return userList;
    }

    public List<Appointment> appointmentsParser(ResultSet resultSet) {
        List<Appointment> appointmentsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getLong("id"));
                appointment.setProcedure(new Procedure(resultSet.getString("procedure_name"), resultSet.getLong("procedure_duration")));
                appointment.setMasterLogin(resultSet.getString("master_login"));
                appointment.setClientLogin(resultSet.getString("client_login"));
                appointment.setStartTime(resultSet.getTimestamp("start_time"));
                appointment.setConfirmed(resultSet.getBoolean("is_confirmed"));
                appointmentsList.add(appointment);
            }
        } catch (SQLException e) {
            logger.error("SQL exception from parser", e);
        }
        return appointmentsList;
    }

    public List<Procedure> procedureParser(ResultSet resultSet) {
        List<Procedure> procedureList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Procedure procedure = new Procedure();
                procedure.setName(resultSet.getString("name"));
                procedure.setDuration(resultSet.getLong("duration"));
                procedureList.add(procedure);
            }
        } catch (SQLException e) {
            logger.error("SQL exception from parser", e);
        }
        return procedureList;
    }
}

