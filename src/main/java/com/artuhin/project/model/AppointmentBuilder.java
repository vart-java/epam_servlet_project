package com.artuhin.project.model;

import java.sql.Timestamp;

public class AppointmentBuilder {
    private static AppointmentBuilder instance;
    private Appointment appointment;

    private AppointmentBuilder() {
        appointment = new Appointment();
    }

    public static synchronized AppointmentBuilder getInstance() {
        if (instance == null) {
            instance = new AppointmentBuilder();
        }
        return instance;
    }

    public AppointmentBuilder setId(long id) {
        appointment.setId(id);
        return instance;
    }

    public AppointmentBuilder setProcedure(Procedure procedure) {
        appointment.setProcedure(procedure);
        return instance;
    }

    public AppointmentBuilder setMasterLogin(String masterLogin) {
        appointment.setMasterLogin(masterLogin);
        return instance;
    }

    public AppointmentBuilder setClientLogin(String clientLogin) {
        appointment.setClientLogin(clientLogin);
        return instance;
    }

    public AppointmentBuilder setStartTime(Timestamp startTime) {
        appointment.setStartTime(startTime);
        return instance;
    }

    public AppointmentBuilder setConfirmed(boolean confirmed) {
        appointment.setConfirmed(confirmed);
        return instance;
    }

    public AppointmentBuilder setPaidUp(boolean paidUp) {
        appointment.setPaidUp(paidUp);
        return instance;
    }

    public AppointmentBuilder setFinished(boolean finished) {
        appointment.setFinished(finished);
        return instance;
    }

    public AppointmentBuilder setRated(boolean rated) {
        appointment.setRated(rated);
        return instance;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
