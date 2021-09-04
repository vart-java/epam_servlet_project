package com.artuhin.project.services;

import com.artuhin.project.model.Procedure;
import com.artuhin.project.model.Role;
import com.artuhin.project.model.User;
import com.artuhin.project.factory.DaoFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public String createUser(User user) {
        return DaoFactory.getInstance().getUsersDao().create(user);
    }

    @Override
    public List<User> getAll() {
        return DaoFactory.getInstance().getUsersDao().getAll();
    }

    @Override
    public boolean delete(String login) {
        return DaoFactory.getInstance().getUsersDao().delete(login);
    }

    @Override
    public List<User> getAllMastersSortByRating() {
        return DaoFactory.getInstance().getUsersDao().getAll().stream().filter(m -> m.getRole().equals(Role.MASTER.toString().toLowerCase(Locale.ROOT))).sorted(Comparator.comparingDouble(User::getRating)).collect(Collectors.toList());
    }

    @Override
    public List<List<User>> getAllMastersBySpecilizationSortByRating() {
        List<Procedure> procedures = DaoFactory.getInstance().getProcedureDao().getAll();
        List<User> masters = DaoFactory.getInstance().getUsersDao().getAll().stream().filter(m -> m.getRole().equals(Role.MASTER)).collect(Collectors.toList());
        List<List<User>> sortMasters = new ArrayList<>();
        procedures.forEach(p -> sortMasters.add(masters.stream().filter(m -> m.getSpecialization().getName().equals(p.getName())).sorted(Comparator.comparingDouble(User::getRating).reversed()).collect(Collectors.toList())));
        return sortMasters;
    }

    @Override
    public boolean updateRating(String login, int recall) {
        User user = DaoFactory.getInstance().getUsersDao().get(login);
        user.updateRating(recall);
        return DaoFactory.getInstance().getUsersDao().update(user);
    }

    @Override
    public boolean updateRole(String login, Role role) {
        User user = DaoFactory.getInstance().getUsersDao().get(login);
        user.setRole(role);
        return DaoFactory.getInstance().getUsersDao().update(user);
    }

    @Override
    public List<List<User>> getAllSortByRole() {
        List<User> users = DaoFactory.getInstance().getUsersDao().getAll();
        List<List<User>> sortUsers = new ArrayList<>();
        for (Role p : Role.values()) {
            sortUsers.add(users.stream().filter(u -> u.getRole().equals(p)).collect(Collectors.toList()));
        }
        return sortUsers;
    }

    @Override
    public boolean updateSpecialization(String login, String specialization) {
        User user = DaoFactory.getInstance().getUsersDao().get(login);
        user.setSpecialization(new Procedure(specialization));
        return DaoFactory.getInstance().getUsersDao().update(user);
    }

    @Override
    public User getByLogin(String login) {
        return DaoFactory.getInstance().getUsersDao().get(login);
    }
}
