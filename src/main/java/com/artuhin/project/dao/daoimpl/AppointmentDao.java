package com.artuhin.project.dao.daoimpl;

import com.artuhin.project.dao.Dao;
import com.artuhin.project.dao.TransactionManager;
import com.artuhin.project.dao.management.ConnectionProxy;
import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.util.rsparser.ResultSetParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements Dao<Appointment> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDao.class);
    private static final String SQL_EXCEPTION = "SQL exception in appointments DAO";
    private static AppointmentDao instance;
    private ProcedureDao procedureDao = ProcedureDao.getInstance();

    private AppointmentDao() {
    }

    public static synchronized AppointmentDao getInstance() {
        if (instance == null) {
            instance = new AppointmentDao();
        }
        return instance;
    }

    @Override
    public Appointment create(Appointment appointment) {
        long id = -1;
        if (!checkFreeTime(appointment)) {
            return appointment.builder().id(id).build();
        }
        String createSql = "INSERT INTO appointments (procedure_id, master_id, client_id, start_time, status) VALUES (?, ?, ?, ?, ?)";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy
                     .prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, appointment.getProcedureId());
            preparedStatement.setLong(2, appointment.getMasterId());
            preparedStatement.setLong(3, appointment.getClientId());
            preparedStatement.setTimestamp(4, appointment.getStartTime());
            preparedStatement.setString(5, appointment.getStatus().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("1");
            }
            appointment.setId(id);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return appointment;
    }

    @Override
    public Appointment get(long id) {
        Appointment appointment = new Appointment();
        String getByIdSql = "SELECT * FROM appointments WHERE id = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(getByIdSql)) {
            preparedStatement.setLong(1, id);
            appointment = ResultSetParser.getInstance().appointmentsParser(preparedStatement.executeQuery())
                    .stream().findAny().orElseThrow(() -> new RuntimeException("Appointment with id: " + id + "is absent"));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return appointment;
    }

    @Override
    public boolean update(Appointment appointment) {
        String updateSql = "UPDATE appointments SET procedure_id = ?, master_id = ?, client_id = ?, start_time = ?, status = ?  WHERE id = ?";
        try (ConnectionProxy connection = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setLong(1, appointment.getProcedureId());
            preparedStatement.setLong(2, appointment.getMasterId());
            preparedStatement.setLong(3, appointment.getClientId());
            preparedStatement.setTimestamp(4, appointment.getStartTime());
            preparedStatement.setString(5, appointment.getStatus().toString());
            preparedStatement.setLong(6, appointment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        String deleteSql = "DELETE FROM appointments WHERE id = ?";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(deleteSql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public List<Appointment> getAll() {
        List<Appointment> resultList = new ArrayList<>();
        String getAllSql = "SELECT * FROM appointments";
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(getAllSql)) {
            resultList = ResultSetParser.getInstance().appointmentsParser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
        }
        return resultList;
    }

    public boolean checkFreeTime(Appointment appointment) {
        List<Appointment> appointments;
        Long appEndTime = Timestamp.valueOf(appointment.getStartTime().toLocalDateTime().plusSeconds(procedureDao.get(appointment.getProcedureId()).getDuration())).getTime();
        String getMasterAppointments = "SELECT * FROM appointments WHERE master_id = ?";
        boolean free = true;
        try (ConnectionProxy connectionProxy = TransactionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connectionProxy.prepareStatement(getMasterAppointments)) {
            preparedStatement.setLong(1, appointment.getMasterId());
            appointments = ResultSetParser.getInstance().appointmentsParser(preparedStatement.executeQuery());
            for (Appointment appointmentTemp : appointments) {
                Long appTempEndTime = Timestamp.valueOf(appointmentTemp.getStartTime().toLocalDateTime().plusSeconds(procedureDao.get(appointment.getProcedureId()).getDuration())).getTime();
                if (appointment.getStartTime().getTime() <= appTempEndTime && appointment.getStartTime().getTime() >= appointmentTemp.getStartTime().getTime()
                        || appEndTime >= appointmentTemp.getStartTime().getTime() && appEndTime <= appTempEndTime) {
                    free = false;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            free = false;
        }
        return free;
    }
}
