package com.artuhin.project.model;

import com.artuhin.project.util.annotations.Column;
import com.artuhin.project.util.annotations.Model;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Objects;

@Model
public class Appointment {

    @Column(name = "id")
    private long id;

    private Procedure procedure;

    @Column(name = "master_login")
    private String masterLogin;

    @Column(name = "client_login")
    private String clientLogin;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "is_confirmed")
    private boolean isConfirmed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public String getMasterLogin() {
        return masterLogin;
    }

    public void setMasterLogin(String masterLogin) {
        this.masterLogin = masterLogin;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Appointment(long id, Procedure procedure, String masterLogin, String clientLogin, Timestamp startTime, Boolean isConfirmed) {
        this.id = id;
        this.procedure = procedure;
        this.masterLogin = masterLogin;
        this.clientLogin = clientLogin;
        this.startTime = startTime;
        this.isConfirmed = isConfirmed;
    }

    public Appointment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment o1 = (Appointment) o;
        return id == o1.id &&
                procedure.equals(o1.procedure) &&
                clientLogin.equals(o1.clientLogin) &&
                masterLogin.equals(o1.masterLogin) &&
                Objects.equals(startTime, o1.startTime) &&
                isConfirmed == o1.isConfirmed;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, procedure, masterLogin, clientLogin, startTime, isConfirmed);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", procedureName='" + procedure.getName() + '\'' +
                ", procedureDuration='" + procedure.getDuration() + '\'' +
                ", masterLogin='" + masterLogin + '\'' +
                ", clientLogin='" + clientLogin + '\'' +
                ", startTime=" + startTime +
                ", isConfirmed" + isConfirmed +
                '}';
    }
}
