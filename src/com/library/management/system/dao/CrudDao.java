/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.dao;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface CrudDao<T, ID> extends SuperDao {
    boolean create(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(ID id) throws Exception;

    int count() throws Exception;

    T get(ID id) throws Exception;

    ArrayList<T> getAll() throws Exception;
}
