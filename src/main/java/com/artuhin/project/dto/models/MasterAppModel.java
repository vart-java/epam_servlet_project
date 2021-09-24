package com.artuhin.project.dto.models;

public class MasterAppModel {
    private long id;
    private String masterName;
    private double masterRating;
    private String procedureName;

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
