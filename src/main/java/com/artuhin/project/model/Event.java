package com.artuhin.project.model;

import com.artuhin.project.util.annotations.Column;
import com.artuhin.project.util.annotations.Model;

import java.sql.Timestamp;
import java.util.Objects;

@Model
public class Event {

    @Column(name = "id")
    private long id;

    @Column(name = "procedure_id")
    private long procedureId;

    @Column(name = "master_id")
    private long masterId;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "client_id")
    private long clientId;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

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

    public Event(long id, int master_id, int procedureId, int clientId, Timestamp startTime) {
        this.id = id;
        this.procedureId = procedureId;
        this.masterId = master_id;
        this.clientId = clientId;
        this.startTime = startTime;
    }

    public Event() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event o1 = (Event) o;
        return id == o1.id &&
                procedureId == o1.procedureId &&
                clientId == o1.clientId &&
                masterId == o1.masterId &&
                Objects.equals(startTime, o1.startTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, procedureId, masterId, clientId, startTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", serviceId='" + procedureId + '\'' +
                ", masterId='" + masterId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
