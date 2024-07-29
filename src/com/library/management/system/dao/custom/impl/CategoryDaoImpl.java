/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.dao.custom.impl;

import com.library.management.system.dao.CrudUtil;
import com.library.management.system.dao.custom.CategoryDao;
import com.library.management.system.entity.CategoryEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public boolean create(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO categories (id , name) VALUES (?, ?)",
                t.getId(), t.getName());
    }

    @Override
    public boolean update(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE categories SET name = ?  WHERE id = ?",
                t.getName(), t.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM categories WHERE id = ?", id);
    }

    @Override
    public CategoryEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM categories WHERE id = ?", id);
        if (rst.next()) {
            CategoryEntity entity = new CategoryEntity(rst.getString("id"), rst.getString("name"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {
        ArrayList<CategoryEntity> CategoryEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM categories");
        while (rst.next()) {
            CategoryEntity entity = new CategoryEntity(rst.getString("id"), rst.getString("name"));
            CategoryEntities.add(entity);
        }
        return CategoryEntities;
    }

    @Override
    public int count() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT count(id) as count FROM categories");
        if (rst.next()) {
            return rst.getInt("count");
        }
        return 0;
    }
}
