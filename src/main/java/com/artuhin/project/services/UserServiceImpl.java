package com.artuhin.project.services;

import com.artuhin.project.model.Role;
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
    public List<User> getAllMastersSortByRating() {
        return DaoFactory.getInstance().getUserDao().getAllMastersSortByRating();
    }

    @Override
    public List<List<User>> getAllMastersBySpecilizationSortByRating() {
        return DaoFactory.getInstance().getUserDao().getAllMastersBySpecializationSortByRating();
    }

    @Override
    public boolean checkUser(String login, String password) {
        return DaoFactory.getInstance().getUserDao().chekingIfUserExisting(login, password);
    }

    @Override
    public boolean updateRating(String login, int recall) {
        return DaoFactory.getInstance().getUserDao().updateRating(login, recall);
    }

    @Override
    public boolean updateRole(String login, Role role) {
        return DaoFactory.getInstance().getUserDao().updateRole(login, role);
    }

    @Override
    public List<List<User>> getAllSortByRole() {
        return DaoFactory.getInstance().getUserDao().getAllUserSortByRole();
    }

    @Override
    public User getByLogin(String login) {
        return DaoFactory.getInstance().getUserDao().getByLogin(login);
    }
}
