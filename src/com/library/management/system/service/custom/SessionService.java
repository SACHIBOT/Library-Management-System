/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.custom;

import com.library.management.system.dto.SessionDto;
import com.library.management.system.service.SuperService;

/**
 *
 * @author Lenovo
 */
public interface SessionService extends SuperService {

    boolean logInUser(SessionDto sessionDto) throws Exception;

    boolean logOutUser() throws Exception;

    SessionDto getLoggedUser() throws Exception;

    boolean updateSession(SessionDto sessionDto) throws Exception;
}
