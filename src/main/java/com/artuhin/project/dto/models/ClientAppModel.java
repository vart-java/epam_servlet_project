package com.artuhin.project.dto.models;

import com.artuhin.project.model.enums.Status;

public class ClientAppModel {
    private String procedureName;
    private String masterName;
    private String startDateTime;
    private String endDateTime;
    private Status status;

    public String getProcedureName() { return procedureName; }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
