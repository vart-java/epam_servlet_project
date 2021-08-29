package com.artuhin.project.services;

import com.artuhin.project.dao.AppointmentsDao;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.model.Appointment;
import com.artuhin.project.model.EMailData;

import java.sql.Timestamp;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private static NotificationServiceImpl instance;
    private static AppointmentsDao appointmentsDao = DaoFactory.getInstance().getAppointmentsDao();

    public static synchronized NotificationServiceImpl getInstance() {
        if (instance == null) {
            instance = new NotificationServiceImpl();
        }
        return instance;
    }

    private NotificationServiceImpl() {
    }

    @Override
    public List<EMailData> dataForTrigger(Timestamp timestamp) {
        return DaoFactory.getInstance().getAppointmentsDao().getDataForNotifications(timestamp);
    }
}
