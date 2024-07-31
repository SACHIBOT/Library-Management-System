/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.custom.impl;

import com.library.management.system.dao.DaoFactory;
import com.library.management.system.dao.custom.UserDao;
import com.library.management.system.dto.SessionDto;
import com.library.management.system.entity.UserEntity;
import com.library.management.system.service.custom.SessionService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class SessionServiceImpl implements SessionService {
    private final UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);
    private SessionDto sessionDto = new SessionDto(null, null);

    @Override
    public boolean logInUser(SessionDto sessionDto) throws Exception {
        UserEntity userEntity = userDao.get(sessionDto.getLoggedUserId());
        if (userEntity != null) {
            if (sessionDto.getLoggedUserId().equals(userEntity.getId())
                    || sessionDto.getLoggedUserId().equals(userEntity.getEmail())
                    || sessionDto.getLoggedUserId().equals(userEntity.getName())) {
                if (sessionDto.getLoggedPassword().equals(userEntity.getPassword())) {
                    sessionDto.setLoggedUserId(userEntity.getId());
                    sessionDto.setLoggedUserName(userEntity.getName());
                    sessionDto.setIsLoggedIn(true);
                    sessionDto.setLoggedRole(userEntity.getRole());
                    this.sessionDto = sessionDto;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean logOutUser() throws Exception {
        this.sessionDto = null;
        return true;
    }

    @Override
    public SessionDto getLoggedUser() throws Exception {
        return sessionDto;
    }

    @Override
    public boolean updateSession(SessionDto sessionDto) throws Exception {
        return logInUser(sessionDto);
    }

}
