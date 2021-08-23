package com.artuhin.project.factory;


import com.artuhin.project.services.*;
import com.artuhin.project.services.dynamicproxy.ProxyService;
import com.artuhin.project.util.annotations.Transactional;

import java.util.Arrays;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();
    private static UserService userService = getService(UserServiceImpl.getInstance());
    private static AppointmentsService appointmentsService = getService(AppointmentsServiceImpl.getInstance());
    private static ProcedureService procedureService = getService(ProcedureServiceImpl.getInstance());

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private static <T extends Service> T getService(T t) {
        Class<? extends Service> serviceClass = t.getClass();
        if (isTransactional(serviceClass)) {
            ProxyService<T> proxyService = new ProxyService<>(t, serviceClass);
            return proxyService.getProxy();
        }
        return t;
    }

    public UserService getUserService() {
        return userService;
    }

    public AppointmentsService getAppointmentsService() {
        return appointmentsService;
    }

    public ProcedureService getProcedureService() { return procedureService; }

    private static boolean isTransactional(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Transactional.class));
    }
}
