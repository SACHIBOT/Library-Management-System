/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.dao;

import com.library.management.system.dao.custom.impl.BookDaoImpl;
import com.library.management.system.dao.custom.impl.BorrowingDaoImpl;
import com.library.management.system.dao.custom.impl.CategoryDaoImpl;
import com.library.management.system.dao.custom.impl.UserDaoImpl;

/**
 *
 * @author Lenovo
 */
public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case BOOK:
                return new BookDaoImpl();
            case CATEGORY:
                return new CategoryDaoImpl();
            case USER:
                return new UserDaoImpl();
            case BORROWING:
                return new BorrowingDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoTypes {
        BOOK, CATEGORY, USER,BORROWING;
    }
}
