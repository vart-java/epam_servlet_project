package com.artuhin.project.services.servicesimpl;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.EMailData;
import com.artuhin.project.services.NotificationService;

import java.sql.Timestamp;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private static NotificationServiceImpl instance;

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
        return ServiceFactory.getInstance().getAppointmentsService().getDataForNotifications(timestamp);
    }
}
