package com.artuhin.project.util.rsparser;

import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.enums.Status;
import org.postgresql.util.PGInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultSetParser {
    private static Logger logger = LoggerFactory.getLogger(ResultSetParser.class);
    private static ResultSetParser instance = new ResultSetParser();
    private static final String EXCEPTION = "SQL exception from parser";

    private ResultSetParser() {
    }

    public static ResultSetParser getInstance() {
        return instance;
    }

    public List<User> usersParser(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                userList.add(
                        new User()
                                .builder()
                                .id(resultSet.getLong("id"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("password"))
                                .role(Role.values()[(int) (resultSet.getLong("role_id")-1)])
                                .rating(resultSet.getDouble("rating"))
                                .recallCount(resultSet.getInt("recall_count"))
                                .build()
                );
            }
        } catch (SQLException e) {
            logger.error(EXCEPTION, e);
        }
        return userList;
    }

    public List<Appointment> appointmentsParser(ResultSet resultSet) {
        List<Appointment> appointmentsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                appointmentsList.add(
                        new Appointment()
                                .builder()
                                .id(resultSet.getLong("id"))
                                .procedureId(resultSet.getLong("procedure_id"))
                                .clientId(resultSet.getLong("client_id"))
                                .masterId(resultSet.getLong("master_id"))
                                .startTime(resultSet.getTimestamp("start_time"))
                                .status(Status.valueOf(resultSet.getString("status").toUpperCase(Locale.ROOT)))
                                .build()
                );
            }
        } catch (SQLException e) {
            logger.error(EXCEPTION, e);
        }
        return appointmentsList;
    }

    public List<Procedure> procedureParser(ResultSet resultSet) {
        List<Procedure> procedureList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PGInterval pgInterval = (PGInterval) resultSet.getObject("duration");
                long duration = pgInterval.getHours()*3600+pgInterval.getMinutes()*60;
                procedureList.add(
                        new Procedure()
                                .builder()
                                .id(resultSet.getLong("id"))
                                .name(resultSet.getString("name"))
                                .duration(duration)
                                .build()
                );
            }
        } catch (SQLException e) {
            logger.error(EXCEPTION, e);
        }
        return procedureList;
    }
}

