package com.artuhin.project.services;

import com.artuhin.project.model.User;
import com.artuhin.project.util.annotations.Transactional;
import com.artuhin.project.factory.DaoFactory;

import java.sql.Timestamp;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl ourInstance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return ourInstance;
    }

    private UserServiceImpl() {
    }

    @Override
    public int createUser(User user) {
        return DaoFactory.getInstance().getUserDao().create(user);
    }

    @Override
    public User getUserByID(int id) {
        return DaoFactory.getInstance().getUserDao().getByID(id);
    }

    @Override
    public List<User> getAll() {
        return DaoFactory.getInstance().getUserDao().getAll();
    }

    @Override
    public boolean clearAll() {
        return DaoFactory.getInstance().getUserDao().clearAll();
    }

    @Override
    public boolean updateUser(User user) {
        return DaoFactory.getInstance().getUserDao().update(user);
    }

    @Override
    public boolean delete(int id) {
        return DaoFactory.getInstance().getUserDao().delete(id);
    }

    @Override
    public boolean addUsersToEvent(int clientId, int serviceId, int masterId, Timestamp timestamp) {
        return DaoFactory.getInstance().getUserDao().registerUserToEvent(clientId, serviceId, masterId, timestamp);
    }

    @Override
    public List<User> getMastersByRating() {
        return DaoFactory.getInstance().getUserDao().getMastersByRating();
    }

    @Override
    public List<User> getUsersByLogin() {
        return DaoFactory.getInstance().getUserDao().getMastersByLogins();
    }


    @Override
    public boolean checkUser(String login, String password) {
        return DaoFactory.getInstance().getUserDao().authUser(login, password);
    }

    @Override
    public User getByLogin(String login) {
        return DaoFactory.getInstance().getUserDao().getByLogin(login);
    }


    @Transactional
    @Override
    public boolean vote(int eventId, int rating){
        return DaoFactory.getInstance().getUserDao().vote(eventId,rating);
    }
}
