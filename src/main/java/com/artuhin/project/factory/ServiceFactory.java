package com.artuhin.project.factory;


import com.artuhin.project.services.dynamicproxy.ProxyService;
import com.artuhin.project.util.annotations.Transactional;
import com.artuhin.project.services.AppointmentsService;
import com.artuhin.project.services.AppointmentsServiceImpl;
import com.artuhin.project.services.Service;
import com.artuhin.project.services.UserService;
import com.artuhin.project.services.UserServiceImpl;

import java.util.Arrays;

public class ServiceFactory {

    private static ServiceFactory ourInstance = new ServiceFactory();
    private static UserService userService = getService(UserServiceImpl.getInstance());
    private static AppointmentsService appointmentsService = getService(AppointmentsServiceImpl.getInstance());

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }

    private static <T extends Service> T getService(T t) {
        Class<? extends Service> serviceClass = t.getClass();
        if (isTransactional(serviceClass)) {
            ProxyService<T> proxyService = new ProxyService<>(t, serviceClass);
            return proxyService.getProxy();
        }
        return t;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getOurInstance() {
        return ourInstance;
    }

    public static AppointmentsService getEventsService() {
        return appointmentsService;
    }

    private static boolean isTransactional(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Transactional.class));
    }
}
