package com.artuhin.project.services.servicesimpl;

import com.artuhin.project.dao.daoimpl.AppointmentDao;
import com.artuhin.project.dao.daoimpl.ProcedureDao;
import com.artuhin.project.dao.daoimpl.UserDao;
import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.model.EMailData;
import com.artuhin.project.model.enums.Status;
import com.artuhin.project.services.AppointmentsService;
import com.artuhin.project.util.annotations.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsServiceImpl implements AppointmentsService {
    private static AppointmentsServiceImpl instance;
    private AppointmentDao appointmentDao = DaoFactory.getInstance().getAppointmentDao();
    private ProcedureDao procedureDao = DaoFactory.getInstance().getProcedureDao();
    private UserDao userDao = DaoFactory.getInstance().getUsersDao();

    public static synchronized AppointmentsServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppointmentsServiceImpl();
        }
        return instance;
    }

    private AppointmentsServiceImpl() {
    }

    @Transactional
    @Override
    public Appointment create(Appointment appointment) {
        return appointmentDao.create(appointment);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        return appointmentDao.delete(id);
    }

    @Override
    public Appointment getById(long id) {
        return appointmentDao.get(id);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentDao.getAll();
    }

    @Override
    public boolean updateStartTime(long id, LocalTime localTime) {
        Appointment appointment = appointmentDao.get(id);
        LocalDate localDate = appointment.getStartTime().toLocalDateTime().toLocalDate();
        Timestamp newStart = Timestamp.valueOf(LocalDateTime.of(localDate, localTime));
        appointment.setStartTime(newStart);
        if (appointmentDao.checkFreeTime(appointment)) {
            return appointmentDao.update(appointment);
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateStatus(long id, Status check) {
        Status status = appointmentDao.get(id).getStatus();
        if (!check.equals(status)){
            return false;
        }
        for (Status st : Status.values()) {
            if (status.ordinal() + 1 == st.ordinal()) {
                status = st;
                break;
            }
        }
        Appointment appointment = appointmentDao.get(id);
        appointmentDao.update(appointment.builder().status(status).build());
        return true;
    }

    @Override
    public List<EMailData> getDataForNotifications(Timestamp timestamp) {
        List<EMailData> eMailDatas = new ArrayList<>();
        List<Appointment> appointments = getAll();
        for (Appointment a : appointments) {
            if (a.getStartTime().toLocalDateTime().getDayOfYear() + 1 == timestamp.toLocalDateTime().getDayOfYear() && a.getStatus().equals(Status.PAID)) {
                EMailData eMailData = new EMailData();
                eMailData.setAppointmentId(a.getId());
                eMailData.setMasterLogin(userDao.get(a.getMasterId()).getLogin());
                eMailData.setProcedureName(procedureDao.get(a.getProcedureId()).getName());
                eMailData.setUserLogin(userDao.get(a.getClientId()).getLogin());
                eMailData.setTimestamp(a.getStartTime());
                eMailDatas.add(eMailData);
            }
        }
        return eMailDatas;
    }
}
