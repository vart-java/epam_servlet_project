package com.artuhin.project.model.builder;

import com.artuhin.project.model.enums.Status;
import com.artuhin.project.model.entity.Appointment;

import java.sql.Timestamp;

public class AppointmentBuilder {
    private Appointment appointment;
    public AppointmentBuilder(Appointment appointment) {
        this.appointment = appointment;
    }

    public AppointmentBuilder id(long id) {
        appointment.setId(id);
        return this;
    }

    public AppointmentBuilder procedureId(long procedureId) {
        appointment.setProcedureId(procedureId);
        return this;
    }

    public AppointmentBuilder clientId(long clientId) {
        appointment.setClientId(clientId);
        return this;
    }

    public AppointmentBuilder masterId(long masterId) {
        appointment.setMasterId(masterId);
        return this;
    }

    public AppointmentBuilder startTime(Timestamp startTime) {
        appointment.setStartTime(startTime);
        return this;
    }

    public AppointmentBuilder status(Status status) {
        appointment.setStatus(status);
        return this;
    }

    public Appointment build() {
        return appointment;
    }
}
