package com.artuhin.project.notification.trigger;

import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.EMailData;
import com.artuhin.project.notification.MailSender;
import com.artuhin.project.services.NotificationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class TaskExecutor implements Job {
    private static NotificationService notificationService = ServiceFactory.getNotificationService();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Timestamp time = new Timestamp(new Date().getTime());
        List<EMailData> mailData = notificationService.dataForTrigger(time);
        mailData.forEach(m -> MailSender.getInstance().sendMail(m));
    }
}
