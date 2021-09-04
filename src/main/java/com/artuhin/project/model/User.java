package com.artuhin.project.model;

import org.apache.commons.math3.util.Precision;

import java.util.Locale;
import java.util.Objects;

public class User {
    private String login;
    private String password;
    private Role role;
    private double rating;
    private int recallCount;
    private String simpleName;
    private Procedure specialization;

    public User() {
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
        rating = 0;
    }

    public User(String login, String password, Role role, int rating, String simpleName, Procedure procedure) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.rating = rating;
        this.simpleName = simpleName;
        this.specialization = procedure;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        simpleName = login.substring(0, 1).toUpperCase(Locale.ROOT) + login.substring(1, login.indexOf('@'));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public double getSimpleRating() {
        return Precision.round(rating, 2);
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public Procedure getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Procedure specialization) {
        this.specialization = specialization;
    }

    public int getRecallCount() {
        return recallCount;
    }

    public void setRecallCount(int recall_count) {
        this.recallCount = recall_count;
    }

    public void updateRating(int recall) {
        if (rating == 0 || recallCount == 0) {
            rating = recall;
            recallCount = 1;
        } else {
            rating = (rating * recallCount + recall) / (++recallCount);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password) &&
                role == user.role &&
                rating == user.rating &&
                recallCount == user.recallCount &&
                specialization.equals(user.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, rating, specialization, recallCount);
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", rating=" + rating +
                ", doingProcedure=" + specialization +
                ", recallCount=" + recallCount +
                '}';
    }
}
