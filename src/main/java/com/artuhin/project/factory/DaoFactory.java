package com.artuhin.project.factory;

import com.artuhin.project.dao.EventsDao;
import com.artuhin.project.dao.ProcedureAo;
import com.artuhin.project.dao.UserDao;

public class DaoFactory {
    private static final UserDao userDao = new UserDao();
    private static final EventsDao eventsDao = new EventsDao();
    private static final ProcedureAo procedureAo = new ProcedureAo();


    private static final DaoFactory ourInstance = new DaoFactory();

    public static DaoFactory getInstance() {
        return ourInstance;
    }

    private DaoFactory() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public EventsDao getEventDao() {
        return eventsDao;
    }

    public ProcedureAo getProcedureAo() {
        return procedureAo;
    }
}
