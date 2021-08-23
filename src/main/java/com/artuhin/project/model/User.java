package com.artuhin.project.model;

import java.util.Locale;
import java.util.Objects;

public class User {
    private String login;
    private String password;
    private Role role;
    private int rating;
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
        simpleName = login.substring(0, 1).toUpperCase(Locale.ROOT)+login.substring(1, login.indexOf('@'));
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password) &&
                role == user.role &&
                rating == user.rating &&
                specialization.equals(user.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, rating, specialization);
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", rating=" + rating +
                ", doingProcedure=" + specialization +
                '}';
    }
}
