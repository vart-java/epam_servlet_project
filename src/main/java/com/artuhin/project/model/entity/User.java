package com.artuhin.project.model.entity;

import com.artuhin.project.model.builder.UserBuilder;
import com.artuhin.project.model.enums.Role;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {
    private static final long serialVersionUID = 4744644561264818811L;
    private long id;
    private String login;
    private String password;
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

    public void updateRating(int recall) {
        if (rating == 0 || recallCount == 0) {
            rating = recall;
            recallCount = 1;
        } else {
            rating = (rating * recallCount + recall) / (++recallCount);
        }
    }

    public String getSimpleName (){
        return login.substring(0, login.indexOf('@'));
    }

    public UserBuilder builder(){
        return new UserBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Double.compare(user.rating, rating) == 0 && recallCount == user.recallCount && login.equals(user.login) && password.equals(user.password) && role == user.role && skills.equals(user.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, rating, recallCount, skills);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", rating=" + rating +
                ", recallCount=" + recallCount +
                ", skills=" + skills +
                '}';
    }
}
