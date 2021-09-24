package com.artuhin.project.services.servicesimpl;

import com.artuhin.project.dao.daoimpl.ProcedureDao;
import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.services.ProcedureService;

import java.util.List;

public class ProcedureServiceImpl implements ProcedureService {
    private static ProcedureServiceImpl instance;
    private ProcedureDao procedureDao = DaoFactory.getInstance().getProcedureDao();

    public static synchronized ProcedureServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProcedureServiceImpl();
        }
        return instance;
    }

    private ProcedureServiceImpl() {
    }

    @Override
    public List<Procedure> getAll() {
        return procedureDao.getAll();
    }

    @Override
    public Procedure getProcedureById(Long id) {
        return procedureDao.get(id);
    }
}
