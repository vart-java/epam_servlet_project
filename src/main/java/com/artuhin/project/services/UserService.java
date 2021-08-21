package com.artuhin.project.services;

import com.artuhin.project.model.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService extends Service {

    String createUser(User user);

    User getByLogin(String login);

    List<User> getAll();

    boolean clearAll();

    boolean updateUser(User user);

    boolean delete(String login);

    List<User> getMastersByRating();

    boolean checkUser(String login, String password);
}
