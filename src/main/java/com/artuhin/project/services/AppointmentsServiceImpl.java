package com.artuhin.project.services;

import com.artuhin.project.model.Appointment;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.model.EMailData;

import java.sql.Timestamp;
import java.time.LocalTime;
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
    public boolean delete(long id) {
        return DaoFactory.getInstance().getAppointmentsDao().delete(id);
    }

    @Override
    public Appointment getById(long id) {
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

    @Override
    public List<Appointment> getByMasterLoginByDay(String login, int day) {
        return DaoFactory.getInstance().getAppointmentsDao().getAppointmentByMasterLoginByDay(login, day);
    }

    @Override
    public boolean updateConfirmToTrue(long id) {
        return DaoFactory.getInstance().getAppointmentsDao().updateConfirmToTrue(id);
    }

    @Override
    public boolean updatePaidUpToTrue(long id) {
        return DaoFactory.getInstance().getAppointmentsDao().updatePaidUpToTrue(id);
    }

    @Override
    public boolean updateStartTime(long id, LocalTime localTime) {
        return DaoFactory.getInstance().getAppointmentsDao().updateStartTime(id, localTime);
    }

    @Override
    public boolean updateFinishedToTrue(long id) {
        return DaoFactory.getInstance().getAppointmentsDao().updateFinishedToTrue(id);
    }

    @Override
    public List<EMailData> getDataForNotifications(Timestamp timestamp) {
        return DaoFactory.getInstance().getAppointmentsDao().getDataForNotifications(timestamp);
    }

    @Override
    public boolean updateRatedToTrue(long id) {
        return DaoFactory.getInstance().getAppointmentsDao().updateRatedToTrue(id);
    }


}
