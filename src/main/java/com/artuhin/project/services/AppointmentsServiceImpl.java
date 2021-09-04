package com.artuhin.project.services;

import com.artuhin.project.model.Appointment;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.model.EMailData;
import com.artuhin.project.util.annotations.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentsServiceImpl implements AppointmentsService {
    private static AppointmentsServiceImpl ourInstance = new AppointmentsServiceImpl();

    public static AppointmentsServiceImpl getInstance() {
        return ourInstance;
    }

    private AppointmentsServiceImpl() {
    }


    @Transactional
    @Override
    public int create(Appointment appointment) {
        return DaoFactory.getInstance().getAppointmentDao().create(appointment);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        return DaoFactory.getInstance().getAppointmentDao().delete(id);
    }

    @Override
    public Appointment getById(long id) {
        return DaoFactory.getInstance().getAppointmentDao().get(id);
    }

    @Override
    public List<Appointment> getAll() {
        return DaoFactory.getInstance().getAppointmentDao().getAll();
    }

    @Override
    public List<Appointment> getByClientLogin(String login) {
        return DaoFactory.getInstance().getAppointmentDao().getAll().stream().filter(a -> a.getClientLogin().equals(login)).collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getByMasterLogin(String login) {
        return DaoFactory.getInstance().getAppointmentDao().getAll().stream().filter(a -> a.getMasterLogin().equals(login)).collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getByMasterLoginByDay(String login, int day) {
        return getByMasterLogin(login).stream().filter(a -> a.getStartTime().toLocalDateTime().getDayOfYear() == day).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean updateConfirmToTrue(long id) {
        Appointment appointment = getById(id);
        appointment.setConfirmed(true);
        return DaoFactory.getInstance().getAppointmentDao().update(appointment);
    }

    @Transactional
    @Override
    public boolean updatePaidUpToTrue(long id) {
        Appointment appointment = getById(id);
        appointment.setPaidUp(true);
        return DaoFactory.getInstance().getAppointmentDao().update(appointment);
    }

    @Transactional
    @Override
    public boolean updateStartTime(long id, LocalTime localTime) {
        Appointment appointment = getById(id);
        appointment.setStartTime(Timestamp.valueOf(LocalDateTime.of(appointment.getStartTime().toLocalDateTime().toLocalDate(), localTime)));
        return DaoFactory.getInstance().getAppointmentDao().update(appointment);
    }

    @Transactional
    @Override
    public boolean updateFinishedToTrue(long id) {
        Appointment appointment = getById(id);
        appointment.setFinished(true);
        return DaoFactory.getInstance().getAppointmentDao().update(appointment);
    }

    @Override
    public List<EMailData> getDataForNotifications(Timestamp timestamp) {
        List<EMailData> eMailDatas = new ArrayList<>();
        List<Appointment> appointments = getAll();
        for (Appointment a : appointments) {
            if (a.getStartTime().toLocalDateTime().getDayOfYear() + 1 == timestamp.toLocalDateTime().getDayOfYear() && a.isFinished() && !a.isRated()) {
                EMailData eMailData = new EMailData();
                eMailData.setAppointmentId(a.getId());
                eMailData.setMasterLogin(a.getMasterLogin());
                eMailData.setProcedureName(a.getProcedure().getName());
                eMailData.setUserLogin(a.getClientLogin());
                eMailData.setTimestamp(a.getStartTime());
                eMailDatas.add(eMailData);
            }
        }
        return eMailDatas;
    }

    @Transactional
    @Override
    public boolean updateRatedToTrue(long id) {
        Appointment appointment = getById(id);
        appointment.setRated(true);
        return DaoFactory.getInstance().getAppointmentDao().update(appointment);
    }
}
