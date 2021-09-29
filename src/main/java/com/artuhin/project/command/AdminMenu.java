package com.artuhin.project.command;

import com.artuhin.project.dto.ModelMaker;
import com.artuhin.project.factory.ServiceFactory;
import com.artuhin.project.model.enums.Role;
import com.artuhin.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class AdminMenu implements ICommand {
    private static final String MESSAGE = "message";
    private static final String PAGEID = "pageId";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (!user.getRole().equals(Role.ADMIN)) {
            return "pages/main.jsp";
        }
        if (null != req.getParameter("roleCh") && !ServiceFactory.getInstance().getUserService().updateRole(Long.parseLong(req.getParameter("id")), Role.values()[Integer.parseInt(req.getParameter("roleCh"))])) {
            req.setAttribute(MESSAGE, "sorry_update_role");
        }
        if (null != req.getParameter("delete") && !ServiceFactory.getInstance().getUserService().delete(Long.parseLong(req.getParameter("delete")))) {
            req.setAttribute(MESSAGE, "sorry_delete");
        }
        if (null != req.getParameter("skillCh") && !ServiceFactory.getInstance().getUserService().addSkill(Long.parseLong(req.getParameter("idAddSkill")), Long.parseLong(req.getParameter("skillCh")))) {
            req.setAttribute(MESSAGE, "sorry_update_spec");
        }
        if (null != req.getParameter("userIdDeleteSkill") && !ServiceFactory.getInstance().getUserService().deleteSkill(Long.parseLong(req.getParameter("userIdDeleteSkill")), Long.parseLong(req.getParameter("skillIdDeleteSkill")))) {
            req.setAttribute(MESSAGE, "sorry_update_spec");
        }
        req.setAttribute("roles", Arrays.stream(Role.values()).collect(Collectors.toList()));
        req.setAttribute("procedures", ModelMaker.getInstance().getProcedureDtos(ServiceFactory.getInstance().getProcedureService().getAll()));
        int page = 1;
        int totalPage = ModelMaker.getInstance().getFullUserPaginationNumbers(ServiceFactory.getInstance().getUserService().getAll())+1;
        List<Integer> total = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
        if (null != req.getParameter(PAGEID) && Integer.parseInt(req.getParameter(PAGEID)) <= totalPage) {
                page = Integer.parseInt(req.getParameter(PAGEID));
            }
        req.setAttribute("paginationNumbers", total);
        req.setAttribute("users", ModelMaker.getInstance().getFullUserDtoList(ServiceFactory.getInstance().getUserService().getAll(), page));
        return "pages/admin_menu.jsp";
    }
}
