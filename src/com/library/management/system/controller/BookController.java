/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.controller;

import com.library.management.system.dto.BookDto;
import com.library.management.system.service.ServiceFactory;
import com.library.management.system.service.custom.BookService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BookController {
    private final BookService bookService = (BookService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceTypes.BOOK);

    public String save(BookDto bookDto) throws Exception {
        return bookService.save(bookDto);
    }

    public String update(BookDto bookDto) throws Exception {
        return bookService.update(bookDto);
    }

    public String delete(String bookId) throws Exception {
        return bookService.delete(bookId);
    }

    public int count() throws Exception {
        return bookService.count();
    }

    public ArrayList<BookDto> getAll() throws Exception {
        return bookService.getAll();
    }

    public BookDto get(String bookId) throws Exception {
        return bookService.get(bookId);
    }
}
