package com.artuhin.project.dto.models;

import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.enums.Role;

import java.util.Set;

public class FullUserModel {
    private long id;
    private String login;
    private Role role;
    private double rating;
    private int recallCount;
    private Set<Procedure> skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRecallCount() {
        return recallCount;
    }

    public void setRecallCount(int recallCount) {
        this.recallCount = recallCount;
    }

    public Set<Procedure> getSkills() {
        return skills;
    }

    public void setSkills(Set<Procedure> skills) {
        this.skills = skills;
    }
}
