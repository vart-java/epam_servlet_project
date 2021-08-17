package com.artuhin.project.model;

import com.artuhin.project.util.annotations.Column;
import com.artuhin.project.util.annotations.Model;

import java.util.Objects;

@Model
public class Procedure {
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private long duration;

    public Procedure() {
    }

    public Procedure(int id, String name, long duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Procedure)) return false;
        Procedure o1 = (Procedure) o;
        return id == o1.id &&
                duration == o1.duration &&
                Objects.equals(name, o1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name=" + name +
                ", duration='" + duration + '\'' +
                '}';
    }
}
