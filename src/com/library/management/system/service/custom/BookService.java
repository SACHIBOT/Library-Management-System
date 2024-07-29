/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.service.custom;

import com.library.management.system.dto.BookDto;
import com.library.management.system.service.SuperService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface BookService extends SuperService {
    String save(BookDto BookDto) throws Exception;

    String update(BookDto BookDto) throws Exception;

    String delete(String BookId) throws Exception;

    BookDto get(String BookId) throws Exception;

    int count() throws Exception;

    ArrayList<BookDto> getAll() throws Exception;
}