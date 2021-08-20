package com.artuhin.project.model;

import com.artuhin.project.util.annotations.Column;
import com.artuhin.project.util.annotations.Model;

import java.util.Objects;

@Model
public class User {

    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private int roleId;

    private Role role;

    private int rating;

    private String simpleName;

    public String getSimpleName() {
        if (login != null){
            simpleName = login.substring(0, login.indexOf('@'));
        }
        return simpleName;
    }

    public User() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        rating = 0;
    }

    public Role getRole() {
        if (role != null) {
            return role;
        }
        if (roleId != 0){
            Role[] roles = Role.class.getEnumConstants();
            for (Role role: roles) {
                if (role.getId() == roleId){
                    this.role = role;
                }
            }
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        this.roleId = role.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoleId() {
        return role.getId();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        simpleName = login.substring(0, login.indexOf('@'));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                role.getId() == user.role.getId() &&
                login.equals(user.login) &&
                rating == user.rating &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role, login, password, rating);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", rating=" + rating +
                '}';
    }
}
