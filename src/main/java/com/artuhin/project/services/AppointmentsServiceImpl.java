package com.artuhin.project.services;

import com.artuhin.project.model.Appointment;
import com.artuhin.project.factory.DaoFactory;

import java.util.List;

public class AppointmentsServiceImpl implements AppointmentsService {
    private static AppointmentsServiceImpl ourInstance = new AppointmentsServiceImpl();

    public static AppointmentsServiceImpl getInstance() {
        return ourInstance;
    }

    private AppointmentsServiceImpl() {
    }


    @Override
    public int create(Appointment appointment) {
        return DaoFactory.getInstance().getAppointmentsDao().create(appointment);
    }

    @Override
    public boolean update(Appointment appointment) {
        return DaoFactory.getInstance().getAppointmentsDao().update(appointment);
    }

    @Override
    public boolean delete(int id) {
        return DaoFactory.getInstance().getAppointmentsDao().delete(id);
    }

    @Override
    public Appointment getById(int id) {
        return DaoFactory.getInstance().getAppointmentsDao().getByID(id);
    }

    @Override
    public boolean clearAll() {
        return DaoFactory.getInstance().getAppointmentsDao().clearAll();
    }

    @Override
    public List<Appointment> getAll() {
        return DaoFactory.getInstance().getAppointmentsDao().getAll();
    }

    @Override
    public List<Appointment> getByClientLogin(String login) {
        return DaoFactory.getInstance().getAppointmentsDao().getAppointmentsByClientLogin(login);
    }

    @Override
    public List<Appointment> getByMasterLogin(String login) {
        return DaoFactory.getInstance().getAppointmentsDao().getAppointmentByMasterLogin(login);
    }
}
