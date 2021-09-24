package com.artuhin.project.model.builder;

import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.entity.User;
import com.artuhin.project.model.enums.Role;

import java.util.Set;

public class UserBuilder {
    private User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder id(long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder login(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder role(Role role) {
        user.setRole(role);
        return this;
    }

    public UserBuilder rating(double rating) {
        user.setRating(rating);
        return this;
    }

    public UserBuilder recallCount(int recallCount) {
        user.setRecallCount(recallCount);
        return this;
    }

    public UserBuilder skills(Set<Procedure> skills) {
        user.setSkills(skills);
        return this;
    }

    public User build() {
        return user;
    }
}
