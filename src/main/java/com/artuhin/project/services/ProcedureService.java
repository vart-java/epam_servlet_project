package com.artuhin.project.services;

import com.artuhin.project.model.Procedure;

import java.util.List;

public interface ProcedureService extends Service{
    List<Procedure> getAll();
    Procedure getProcedureByName(String name);
}
