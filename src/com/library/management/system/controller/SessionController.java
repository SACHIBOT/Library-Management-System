package com.library.management.system.controller;

import com.library.management.system.dto.SessionDto;
import com.library.management.system.service.ServiceFactory;
import com.library.management.system.service.custom.SessionService;

public class SessionController {
    private final SessionService sessionService = (SessionService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceTypes.SESSION);

    public boolean logInUser(SessionDto sessionDto) throws Exception {
        return sessionService.logInUser(sessionDto);
    }

    public boolean logOutUser() throws Exception {
        return sessionService.logOutUser();
    }

    public SessionDto getLoggedUser() throws Exception {
        return sessionService.getLoggedUser();
    }

    public boolean updateSession(SessionDto sessionDto) throws Exception {
        return sessionService.updateSession(sessionDto);
    }

}
