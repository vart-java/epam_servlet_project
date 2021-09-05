package com.artuhin.project.notification.trigger;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class Trigger {
    private static Trigger instance;
    private static Logger LOGGER = LoggerFactory.getLogger(Trigger.class);
    private static Scheduler scheduler;

    public static synchronized Trigger getInstance() {
        if (instance == null) {
            instance = new Trigger();
        }
        return instance;
    }

    private Trigger() {
    }

    public static void start() {
        JobDetail jobDetail = JobBuilder.newJob(TaskExecutor.class).build();
        org.quartz.Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("Send mails")
                .startAt(new Date())
                .withSchedule(simpleSchedule()
                .withIntervalInHours(24)
                .repeatForever()).build();
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOGGER.error("exc", e);
        }
    }

    public static void stop() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOGGER.error(String.valueOf(e));
        }
    }
}
