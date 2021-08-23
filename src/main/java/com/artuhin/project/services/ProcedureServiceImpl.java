package com.artuhin.project.services;

import com.artuhin.project.factory.DaoFactory;
import com.artuhin.project.model.Procedure;

import java.util.List;

public class ProcedureServiceImpl implements ProcedureService{
    private static ProcedureServiceImpl instance = new ProcedureServiceImpl();
    private ProcedureServiceImpl(){}

    public static ProcedureServiceImpl getInstance() {
        return instance;
    }


    @Override
    public List<Procedure> getAll() {
        return DaoFactory.getInstance().getProcedureDao().getAll();
    }
}
