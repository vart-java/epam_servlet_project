package com.artuhin.project.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Appointment implements Serializable {

    private long id;
    private Procedure procedure;
    private String masterLogin;
    private String clientLogin;
    private Timestamp startTime;
    private boolean isConfirmed;
    private boolean isPaidUp;
    private boolean isFinished;
    private boolean isRated;

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

    public boolean isPaidUp() {
        return isPaidUp;
    }

    public void setPaidUp(boolean paidUp) {
        isPaidUp = paidUp;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
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
                isConfirmed == o1.isConfirmed &&
                isPaidUp == o1.isPaidUp &&
                isRated == o1.isRated &&
                isFinished == o1.isFinished;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, procedure, masterLogin, clientLogin, startTime, isConfirmed, isPaidUp, isFinished, isRated);
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
                ", isPaidUp" + isPaidUp +
                ", isFinished" + isFinished +
                ", isRated" + isRated +
                '}';
    }
}
