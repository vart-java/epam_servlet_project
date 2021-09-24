package com.artuhin.project.dto.models;

import com.artuhin.project.model.enums.Status;

public class RecallModel {
    private long masterId;
    private long appointmentId;
    private String masterName;
    private double masterRating;
    private String procedureName;
    private Status status;


    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public double getMasterRating() {
        return masterRating;
    }

    public void setMasterRating(double masterRating) {
        this.masterRating = masterRating;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
