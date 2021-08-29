package com.artuhin.project.model;

import java.sql.Timestamp;
import java.util.Locale;

public class EMailData {
    private String userLogin;
    private String procedureName;
    private String masterLogin;
    private Timestamp timestamp;
    private long appointmentId;

    public EMailData() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getSimpleName(String name) {
        return name.substring(0,1).toUpperCase(Locale.ROOT)+name.substring(1, name.indexOf('@'));
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getMasterLogin() {
        return masterLogin;
    }

    public void setMasterLogin(String masterLogin) {
        this.masterLogin = masterLogin;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
