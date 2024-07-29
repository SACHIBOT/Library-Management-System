/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.dao.custom;

import java.util.ArrayList;

import com.library.management.system.dao.CrudDao;
import com.library.management.system.entity.BorrowingEntity;

/**
 *
 * @author Lenovo
 */
public interface BorrowingDao extends CrudDao<BorrowingEntity, String> {

    ArrayList<BorrowingEntity> getTop5byBookId() throws Exception;

    ArrayList<BorrowingEntity> getByBookId(String id) throws Exception;

    ArrayList<BorrowingEntity> getByUserId(String id) throws Exception;

    ArrayList<BorrowingEntity> getByStatus(String status) throws Exception;

}
