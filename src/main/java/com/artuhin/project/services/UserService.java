package com.artuhin.project.services;

import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;

import java.util.List;

public interface UserService extends Service {

    String createUser(User user);

    User getByLogin(String login);

    List<User> getAll();

    boolean delete(String login);

    List<User> getAllMastersSortByRating();

    List<List<User>> getAllMastersBySpecilizationSortByRating();

    boolean updateRating(String login, int recall);

    boolean updateRole(String login, Role role);

    List<List<User>> getAllSortByRole();

    boolean updateSpecialization(String login, String specialization);
}
