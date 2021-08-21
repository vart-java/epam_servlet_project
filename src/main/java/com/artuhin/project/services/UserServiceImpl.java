package com.artuhin.project.services;

import com.artuhin.project.model.User;
import com.artuhin.project.factory.DaoFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl ourInstance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return ourInstance;
    }

    private UserServiceImpl() {
    }

    @Override
    public String createUser(User user) {
        return DaoFactory.getInstance().getUserDao().create(user);
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
    public boolean delete(String login) {
        return DaoFactory.getInstance().getUserDao().delete(login);
    }

    @Override
    public List<User> getMastersByRating() {
        return DaoFactory.getInstance().getUserDao().getAllMastersSortByRating();
    }


    @Override
    public boolean checkUser(String login, String password) {
        return DaoFactory.getInstance().getUserDao().chekingIfUserExisting(login, password);
    }

    @Override
    public User getByLogin(String login) {
        return DaoFactory.getInstance().getUserDao().getByLogin(login);
    }
}
