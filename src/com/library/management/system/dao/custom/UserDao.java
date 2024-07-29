/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.dao.custom;

import com.library.management.system.dao.CrudDao;
import com.library.management.system.entity.UserEntity;

/**
 *
 * @author Lenovo
 */
public interface UserDao extends CrudDao<UserEntity, String> {
    boolean validateUser(String user, String password) throws Exception;
}
