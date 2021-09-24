package com.artuhin.project.model.entity;

import com.artuhin.project.model.builder.AppointmentBuilder;
import com.artuhin.project.model.enums.Status;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Appointment implements Serializable {

    private long id;
    private long procedureId;
    private long masterId;
    private long clientId;
    private Timestamp startTime;
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(long procedureId) {
        this.procedureId = procedureId;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AppointmentBuilder builder() {
        return new AppointmentBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && procedureId == that.procedureId && masterId == that.masterId && clientId == that.clientId && startTime.equals(that.startTime) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, procedureId, masterId, clientId, startTime, status);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", procedureId=" + procedureId +
                ", masterId=" + masterId +
                ", clientId=" + clientId +
                ", startTime=" + startTime +
                ", status=" + status +
                '}';
    }
}
