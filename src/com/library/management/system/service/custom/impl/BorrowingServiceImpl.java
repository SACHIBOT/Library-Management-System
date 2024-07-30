/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.custom.impl;

import com.library.management.system.dao.DaoFactory;
import com.library.management.system.dao.custom.BookDao;
import com.library.management.system.dao.custom.BorrowingDao;
import com.library.management.system.db.DBConnection;
import com.library.management.system.dto.BorrowingDto;
import com.library.management.system.entity.BookEntity;
import com.library.management.system.entity.BorrowingEntity;
import com.library.management.system.service.custom.BorrowingService;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingDao BorrowingDao = (BorrowingDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROWING);
    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOK);

    @Override
    public String save(BorrowingDto borrowingDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            BorrowingEntity entity = getBorrowingEntity(borrowingDto);
            BookEntity bookEntity = bookDao.get(entity.getBookId());

            if (bookEntity.getCopiesQoH() > 0) {
                if (BorrowingDao.create(entity)) {
                    int newCopiesCount = bookEntity.getCopiesQoH() - 1;
                    bookEntity.setCopiesQoH(newCopiesCount);

                    if (bookDao.update(bookEntity)) {
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return "Fail";
                    }
                } else {
                    connection.rollback();
                    return "Fail";
                }
            } else {
                connection.rollback();
                return "Fail";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return "Fail";
        } finally {
            connection.setAutoCommit(true);

        }
    }

    @Override
    public String update(BorrowingDto borrowingDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            BorrowingEntity entity = getBorrowingEntity(borrowingDto);

            if (BorrowingDao.update(entity)) {
                BookEntity bookEntity = bookDao.get(entity.getBookId());
                if (entity.getStatus() == "Returned") {
                    int newCopiesCount = bookEntity.getCopiesQoH() + 1;
                    bookEntity.setCopiesQoH(newCopiesCount);
                    if (bookDao.update(bookEntity)) {
                        connection.commit();
                        return "Success";
                    } else {
                        connection.rollback();
                        return "Fail";
                    }
                } else {
                    return "Success";
                }
            } else {
                connection.rollback();

                return "Fail";
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return "Fail";

        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public String delete(String borrowingId) throws Exception {
        return BorrowingDao.delete(borrowingId) ? "Success" : "Fail";
    }

    @Override
    public BorrowingDto get(String borrowingId) throws Exception {
        BorrowingEntity entity = BorrowingDao.get(borrowingId);
        if (entity != null) {
            return getBorrowingDto(entity);
        }
        return null;
    }

    @Override
    public int count() throws Exception {
        return BorrowingDao.count();
    }

    @Override
    public ArrayList<BorrowingDto> getAll() throws Exception {

        return getBorrowingDtosList(BorrowingDao.getAll());
    }

    private BorrowingEntity getBorrowingEntity(BorrowingDto dto) {
        return new BorrowingEntity(
                dto.getId(),
                dto.getUserId(), dto.getBookId(), dto.getBorrowDate(), dto.getReturnDate(), dto.getStatus());
    }

    private BorrowingDto getBorrowingDto(BorrowingEntity entity) {
        return new BorrowingDto(entity.getId(),
                entity.getUserId(), entity.getBookId(), entity.getBorrowDate(), entity.getReturnDate(),
                entity.getStatus());
    }

    @Override
    public ArrayList<BorrowingDto> getTop5byBookId() throws Exception {

        return getBorrowingDtosList(BorrowingDao.getTop5byBookId());
    }

    @Override
    public ArrayList<BorrowingDto> getByBookId(String id) throws Exception {
        return getBorrowingDtosList(BorrowingDao.getByBookId(id));
    }

    @Override
    public ArrayList<BorrowingDto> getByUserId(String id) throws Exception {
        return getBorrowingDtosList(BorrowingDao.getByUserId(id));
    }

    @Override
    public ArrayList<BorrowingDto> getByStatus(String status) throws Exception {
        return getBorrowingDtosList(BorrowingDao.getByStatus(status));
    }

    private ArrayList<BorrowingDto> getBorrowingDtosList(ArrayList<BorrowingEntity> BorrowingEntities)
            throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            if (BorrowingEntities != null && !BorrowingEntities.isEmpty()) {
                ArrayList<BorrowingDto> borrowingDtos = new ArrayList<>();

                for (BorrowingEntity BorrowingEntity : BorrowingEntities) {
                    LocalDate currentDate = LocalDate.now();
                    LocalDate returnDate = BorrowingEntity.getReturnDate().toLocalDate();

                    if (returnDate.isBefore(currentDate)) {
                        BorrowingEntity.setStatus("Overdue");
                        BorrowingDao.update(BorrowingEntity);
                    }

                    borrowingDtos.add(getBorrowingDto(BorrowingEntity));
                }

                return borrowingDtos;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
