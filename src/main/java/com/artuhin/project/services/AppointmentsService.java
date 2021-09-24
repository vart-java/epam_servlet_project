package com.artuhin.project.services;

import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.model.EMailData;
import com.artuhin.project.model.enums.Status;
import com.artuhin.project.util.annotations.Transactional;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentsService extends Service {

    Appointment create(Appointment appointment);

    boolean delete(long id);

    Appointment getById(long id);

    List<Appointment> getAll();

    boolean updateStartTime(long id, LocalTime localTime);

    boolean updateStatus(long id, Status check);

    List<EMailData> getDataForNotifications(Timestamp timestamp);
}
