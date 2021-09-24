package com.artuhin.project.dto.models;

import java.util.List;

public class RateTableModel {
    private String procedureName;
    private long procedureId;
    private List<MasterModel> masterModelList;

    public long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(long procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public List<MasterModel> getMasterDtoList() {
        return masterModelList;
    }

    public void setMasterDtoList(List<MasterModel> masterModelList) {
        this.masterModelList = masterModelList;
    }
}
