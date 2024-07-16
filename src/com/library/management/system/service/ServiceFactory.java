/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service;

import com.library.management.system.dao.SuperDao;
import com.library.management.system.service.impl.BookServiceImpl;

/**
 *
 * @author Lenovo
 */

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){
        
    }
    
   public static ServiceFactory getInstance(){
       if(serviceFactory == null){
           serviceFactory = new ServiceFactory();
       }
        return serviceFactory;
   }

       public SuperService getService(ServiceTypes type) {
        switch (type) {
            case BOOK:
                return new BookServiceImpl();
            case CATEGORY:
                return null;
            case MEMBER:
                return null; 
            default:
                return null;
        }
    }

    public enum ServiceTypes {
        BOOK, CATEGORY, MEMBER;
    }
}

