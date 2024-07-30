/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.service.custom;

import com.library.management.system.dto.UserDto;
import com.library.management.system.service.SuperService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface UserService extends SuperService {
    String save(UserDto userDto) throws Exception;

    String update(UserDto userDto) throws Exception;

    String delete(String userId) throws Exception;

    UserDto get(String userId) throws Exception;

    int count() throws Exception;

    boolean updatePassword(String currentPassword, String newPassword, String userId) throws Exception;

    boolean validateUser(String user, String password) throws Exception;

    ArrayList<UserDto> getAll() throws Exception;
}
