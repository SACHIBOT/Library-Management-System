/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.controller;

import com.library.management.system.dto.UserDto;
import com.library.management.system.service.ServiceFactory;
import com.library.management.system.service.custom.UserService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class UserController {
    private final UserService userService = (UserService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceTypes.USER);

    public String save(UserDto userDto) throws Exception {
        return userService.save(userDto);
    }

    public String update(UserDto userDto) throws Exception {
        return userService.update(userDto);
    }

    public String delete(String userId) throws Exception {
        return userService.delete(userId);
    }

    public boolean validateUser(String user, String password) throws Exception {
        return userService.validateUser(user, password);
    }

    public int count() throws Exception {
        return userService.count();
    }

    public boolean updatePassword(String currentPassword, String newPassword, String userId) throws Exception {
        return userService.updatePassword(currentPassword, newPassword, userId);
    }

    public ArrayList<UserDto> getAll() throws Exception {
        return userService.getAll();
    }

    public UserDto get(String userId) throws Exception {
        return userService.get(userId);
    }
}
