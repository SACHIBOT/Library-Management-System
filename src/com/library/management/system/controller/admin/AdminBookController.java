/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.controller;

import com.library.management.system.dto.BookDto;
import com.library.management.system.service.ServiceFactory;
import com.library.management.system.service.custom.BookService;
import com.library.management.system.service.impl.BookServiceImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class BookController {
    
    private BookService BookService = (BookService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
    
    public String save(BookDto BookDto) throws Exception{
        return BookService.save(BookDto);
    }
    
    public String update(BookDto BookDto) throws Exception{
        return BookService.update(BookDto);
    }
    
    public String delete(String BookId) throws Exception{
        return BookService.delete(BookId);
    }
    
    public ArrayList<BookDto> getAll() throws Exception{
        return BookService.getAll();
    }
    
    public BookDto get(String BookId) throws Exception{
        return BookService.get(BookId);
    }
    
}
