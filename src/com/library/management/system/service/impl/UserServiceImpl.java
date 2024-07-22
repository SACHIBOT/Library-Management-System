/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.impl;

import com.library.management.system.dao.DaoFactory;
import com.library.management.system.dao.custom.UserDao;
import com.library.management.system.dto.UserDto;
import com.library.management.system.entity.UserEntity;
import com.library.management.system.service.custom.UserService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class UserServiceImpl  implements UserService{
    private final UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);
    @Override
    public String save(UserDto userDto) throws Exception {
        UserEntity entity = getUserEntity(userDto);
        return userDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(UserDto userDto) throws Exception {
        UserEntity entity = getUserEntity(userDto);
        return userDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String userId) throws Exception {
  return userDao.delete(userId) ?  "Success" : "Fail";
    }

    @Override
    public UserDto get(String userId) throws Exception {
        UserEntity entity = userDao.get(userId);
        if(entity != null){
            return getUserDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<UserDto> getAll() throws Exception {
        ArrayList<UserEntity> userEntitys = userDao.getAll();
        if(userEntitys != null && !userEntitys.isEmpty()){
            ArrayList<UserDto> userDtos = new ArrayList<>();
            
            for (UserEntity userEntity : userEntitys) {
                userDtos.add(getUserDto(userEntity));
            }
            
            return userDtos;
        }
        return null;
    }
    
        private UserEntity getUserEntity(UserDto dto){
        return  new UserEntity(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRole()); 
    }
    
    private UserDto getUserDto(UserEntity entity){
        return  new UserDto(entity.getId(),
                entity.getName(),
        entity.getEmail(),
        entity.getPassword(),
        entity.getRole());
    }
    
}
