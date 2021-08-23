package com.artuhin.project.services;

import com.artuhin.project.model.Appointment;
import java.util.List;

public interface AppointmentsService extends Service {

    int create(Appointment appointment);

    boolean update(Appointment appointment);

    boolean delete(int id);

    Appointment getById(int id);

    boolean clearAll();

    List<Appointment> getAll();

    List<Appointment> getByClientLogin(String login);

    List<Appointment> getByMasterLogin(String login);

    List<Appointment> getByMasterLoginByDay(String login, int day);


}
