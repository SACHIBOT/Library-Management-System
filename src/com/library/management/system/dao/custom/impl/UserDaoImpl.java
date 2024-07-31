/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.dao.custom.impl;

import com.library.management.system.dao.CrudUtil;
import com.library.management.system.dao.custom.UserDao;
import com.library.management.system.entity.UserEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class UserDaoImpl implements UserDao {

    @Override
    public boolean create(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO users (id , name, email, password, role) VALUES (?, ?, ?, ?, ?)",
                t.getId(), t.getName(), t.getEmail(), t.getPassword(), t.getRole());
    }

    @Override
    public boolean update(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE id = ?",
                t.getName(), t.getEmail(), t.getPassword(), t.getRole(), t.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public UserEntity get(String user) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM users WHERE id = ? OR name = ? OR email = ?", user, user,
                user);
        if (rst.next()) {
            UserEntity entity = new UserEntity(rst.getString("id"), rst.getString("name"),
                    rst.getString("email"), rst.getString("password"), rst.getString("role"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<UserEntity> getAll() throws Exception {
        ArrayList<UserEntity> UserEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM users");
        while (rst.next()) {
            UserEntity entity = new UserEntity(rst.getString("id"), rst.getString("name"),
                    rst.getString("email"), rst.getString("password"), rst.getString("role"));
            UserEntities.add(entity);
        }
        return UserEntities;
    }

    @Override
    public boolean validateUser(String user, String password) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM users WHERE (email = ? OR name = ? OR id = ?) AND password = ?", user, user, user,
                password);

        return rst.next();
    }

    @Override
    public int count() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT count(id) as count FROM users");
        if (rst.next()) {
            return rst.getInt("count");
        }
        return 0;
    }

}
