package com.artuhin.project.services;

import com.artuhin.project.model.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService extends Service {

    int createUser(User user);

    User getUserByID(int id);

    List<User> getAll();

    boolean clearAll();

    boolean updateUser(User user);

    boolean delete(int id);

    boolean addUsersToEvent(int serviceId, int masterId, int clientId, Timestamp timestamp);

    List<User> getMastersByRating();

    List<User> getUsersByLogin();

    boolean checkUser(String login, String password);

    User getByLogin(String login);

    boolean vote(int eventId, int rating);
}
