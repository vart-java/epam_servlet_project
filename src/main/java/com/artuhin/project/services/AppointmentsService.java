package com.artuhin.project.services;

import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.EMailData;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentsService extends Service {

    int create(Appointment appointment);

    boolean update(Appointment appointment);

    boolean delete(long id);

    Appointment getById(long id);

    boolean clearAll();

    List<Appointment> getAll();

    List<Appointment> getByClientLogin(String login);

    List<Appointment> getByMasterLogin(String login);

    List<Appointment> getByMasterLoginByDay(String login, int day);

    boolean updateConfirmToTrue(long id);

    boolean updatePaidUpToTrue(long id);

    boolean updateStartTime(long id, LocalTime localTime);

    boolean updateFinishedToTrue(long id);

    List<EMailData> getDataForNotifications(Timestamp timestamp);

    boolean updateRatedToTrue(long id);
}
