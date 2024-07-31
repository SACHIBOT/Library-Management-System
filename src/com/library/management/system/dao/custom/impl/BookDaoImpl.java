/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.dao.custom.impl;

import com.library.management.system.dao.CrudUtil;
import com.library.management.system.dao.custom.BookDao;
import com.library.management.system.entity.BookEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BookDaoImpl implements BookDao {

    @Override
    public boolean create(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO books (id, title, author, category_id, copies_qoh, image_path) VALUES (?, ?, ?, ?, ?, ?)",
                t.getId(), t.getTitle(), t.getAuthor(), t.getCategoryId(), t.getCopiesQoH(), t.getImagePath());
    }

    @Override
    public boolean update(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE books SET title = ?, author = ?, category_id = ?, copies_qoh = ?, image_path = ? WHERE id = ?",
                t.getTitle(), t.getAuthor(), t.getCategoryId(), t.getCopiesQoH(), t.getImagePath(), t.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM books WHERE id = ?", id);
    }

    @Override
    public BookEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM books WHERE id = ? or title = ?", id, id);
        if (rst.next()) {
            BookEntity entity = new BookEntity(rst.getString("id"), rst.getString("title"),
                    rst.getString("author"), rst.getString("category_id"), rst.getInt("copies_qoh"),
                    rst.getString("image_path"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        ArrayList<BookEntity> BookEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM books");
        while (rst.next()) {
            BookEntity entity = new BookEntity(rst.getString("id"), rst.getString("title"),
                    rst.getString("author"), rst.getString("category_id"), rst.getInt("copies_qoh"),
                    rst.getString("image_path"));
            BookEntities.add(entity);
        }
        return BookEntities;
    }

    @Override
    public int count() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT count(id) as count FROM books");
        if (rst.next()) {
            return rst.getInt("count");
        }
        return 0;
    }
}