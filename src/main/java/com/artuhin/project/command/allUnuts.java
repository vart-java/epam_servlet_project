package com.artuhin.project.command;

import com.artuhin.project.dto.ModelMaker;
import com.artuhin.project.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class allUnuts implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        LocalDate localDate = LocalDate.now();
        if (null != req.getParameter("sort")) {
            req.setAttribute("masters_entity", ModelMaker.getInstance().sortMastersAppDtoList(req.getParameter("sort"), ModelMaker.getInstance().getMasterAppDtos(ServiceFactory.getInstance().getUserService().getAll())));
        } else {
            req.setAttribute("masters_entity", ModelMaker.getInstance().getMasterAppDtos(ServiceFactory.getInstance().getUserService().getAll()));
        }
        if (null != req.getParameter("userFilter")) {
            req.setAttribute("masters_entity", ModelMaker.getInstance().filterMastersAppDtoList(req.getParameter("userFilter"), req.getParameter("filterId"), ModelMaker.getInstance().getMasterAppDtos(ServiceFactory.getInstance().getUserService().getAll())));
        }
        req.setAttribute("dateNow1", localDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        req.setAttribute("dateNow7", localDate.plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "pages/all_units.jsp";
    }
}

