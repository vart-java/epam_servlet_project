package com.artuhin.project.model;

import com.artuhin.project.util.annotations.Column;
import com.artuhin.project.util.annotations.Model;

import java.sql.Timestamp;
import java.util.Objects;

@Model
public class Event {

    @Column(name = "id")
    private int id;

    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "master_id")
    private int masterId;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "client_id")
    private int clientId;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Event(int id, int master_id, int serviceId, int clientId, Timestamp startTime) {
        this.id = id;
        this.serviceId = serviceId;
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
                serviceId == o1.serviceId &&
                clientId == o1.clientId &&
                masterId == o1.masterId &&
                Objects.equals(startTime, o1.startTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, serviceId, masterId, clientId, startTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", serviceId='" + serviceId + '\'' +
                ", masterId='" + masterId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
