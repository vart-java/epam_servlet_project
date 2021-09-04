package com.artuhin.project.factory;

import com.artuhin.project.dao.AppointmentsDao;
import com.artuhin.project.dao.ProcedureDao;
import com.artuhin.project.dao.UsersDao;

public class DaoFactory {
    private UsersDao USERS_DAO = UsersDao.getInstance();
    private static final AppointmentsDao APPOINTMENTS_DAO = new AppointmentsDao();
    private static final ProcedureDao PROCEDURE_DAO = new ProcedureDao();

    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    private DaoFactory() {
    }


    public UsersDao getUsersDao() {
        return USERS_DAO;
    }

    public AppointmentsDao getAppointmentsDao() {
        return APPOINTMENTS_DAO;
    }

    public ProcedureDao getProcedureDao() {
        return PROCEDURE_DAO;
    }
}
