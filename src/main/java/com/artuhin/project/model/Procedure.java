package com.artuhin.project.model;

import java.io.Serializable;
import java.util.Objects;

public class Procedure implements Serializable {

    private static final long serialVersionUID = -7162310034856761580L;
    private String name;
    private long duration;

    public Procedure() {
    }

    public Procedure(String name) {
        this.name = name;
    }

    public Procedure(String name, long duration) {
        this.name = name;
        this.duration = duration;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Procedure)) return false;
        Procedure o1 = (Procedure) o;
        return duration == o1.duration &&
                Objects.equals(name, o1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }

    @Override
    public String toString() {
        return "Procedure{" +
                ", name=" + name +
                ", duration='" + duration + '\'' +
                '}';
    }
}
