/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.custom.impl;

import com.library.management.system.dao.DaoFactory;
import com.library.management.system.dao.custom.BookDao;
import com.library.management.system.dto.BookDto;
import com.library.management.system.entity.BookEntity;
import com.library.management.system.service.custom.BookService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BookServiceImpl implements BookService {

    private BookDao BookDao = (BookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOK);

    @Override
    public String save(BookDto BookDto) throws Exception {
        BookEntity entity = getBookEntity(BookDto);
        return BookDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(BookDto BookDto) throws Exception {
        BookEntity entity = getBookEntity(BookDto);
        return BookDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String BookId) throws Exception {
        return BookDao.delete(BookId) ? "Success" : "Fail";
    }

    @Override
    public BookDto get(String BookId) throws Exception {
        BookEntity entity = BookDao.get(BookId);
        if (entity != null) {
            return getBookDto(entity);
        }
        return null;
    }

    @Override
    public int count() throws Exception {
        return BookDao.count();
    }

    @Override
    public ArrayList<BookDto> getAll() throws Exception {
        ArrayList<BookEntity> BookEntities = BookDao.getAll();
        if (BookEntities != null && !BookEntities.isEmpty()) {
            ArrayList<BookDto> BookDtos = new ArrayList<>();

            for (BookEntity BookEntity : BookEntities) {
                BookDtos.add(getBookDto(BookEntity));
            }

            return BookDtos;
        }
        return null;
    }

    private BookEntity getBookEntity(BookDto BookDto) {
        return new BookEntity(
                BookDto.getId(),
                BookDto.getTitle(),
                BookDto.getAuthor(),
                BookDto.getCategoryId(),
                BookDto.getCopiesQoH(),
                BookDto.getImagePath());
    }

    private BookDto getBookDto(BookEntity entity) {
        return new BookDto(entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getCategoryId(),
                entity.getCopiesQoH(),
                entity.getImagePath());
    }

}
