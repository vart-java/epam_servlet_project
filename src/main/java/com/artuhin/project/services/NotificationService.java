package com.artuhin.project.services;

import com.artuhin.project.model.EMailData;

import java.sql.Timestamp;
import java.util.List;

public interface NotificationService extends Service {
    List<EMailData> dataForTrigger(Timestamp timestamp);
}
