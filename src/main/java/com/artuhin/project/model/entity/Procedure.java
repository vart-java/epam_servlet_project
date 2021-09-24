package com.artuhin.project.model.entity;

import com.artuhin.project.model.builder.ProcedureBuilder;

import java.io.Serializable;
import java.util.Objects;

public class Procedure implements Serializable {

    private static final long serialVersionUID = -7162310034856761580L;
    private long id;
    private String name;
    private long duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public ProcedureBuilder builder (){
        return new ProcedureBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return id == procedure.id && duration == procedure.duration && name.equals(procedure.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
