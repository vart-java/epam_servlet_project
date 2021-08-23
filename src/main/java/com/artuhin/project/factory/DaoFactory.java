package com.artuhin.project.factory;

import com.artuhin.project.dao.AppointmentsDao;
import com.artuhin.project.dao.ProcedureDao;
import com.artuhin.project.dao.UserDao;

public class DaoFactory {
    private static final UserDao userDao = new UserDao();
    private static final AppointmentsDao APPOINTMENTS_DAO = new AppointmentsDao();
    private static final ProcedureDao procedureDao = new ProcedureDao();


    private static final DaoFactory ourInstance = new DaoFactory();

    public static DaoFactory getInstance() {
        return ourInstance;
    }

    private DaoFactory() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public AppointmentsDao getAppointmentsDao() {
        return APPOINTMENTS_DAO;
    }

    public ProcedureDao getProcedureDao() {
        return procedureDao;
    }
}
