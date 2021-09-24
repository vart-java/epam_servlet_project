package com.artuhin.project.model.builder;

import com.artuhin.project.model.entity.Procedure;

public class ProcedureBuilder {
    private Procedure procedure;

    public ProcedureBuilder(Procedure procedure) {
        this.procedure = procedure;
    }

    public ProcedureBuilder id(long id) {
        procedure.setId(id);
        return this;
    }

    public ProcedureBuilder name(String name) {
        procedure.setName(name);
        return this;
    }

    public ProcedureBuilder duration(long duration) {
        procedure.setDuration(duration);
        return this;
    }

    public Procedure build() {
        return procedure;
    }
}
