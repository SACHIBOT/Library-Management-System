/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.impl;

import com.library.management.system.dao.DaoFactory;
import com.library.management.system.dao.custom.BorrowingDao;
import com.library.management.system.dto.BorrowingDto;
import com.library.management.system.entity.BorrowingEntity;
import com.library.management.system.service.custom.BorrowingService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingDao BorrowingDao = (BorrowingDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROWING);

    @Override
    public String save(BorrowingDto borrowingDto) throws Exception {
        BorrowingEntity entity = getBorrowingEntity(borrowingDto);
        return BorrowingDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(BorrowingDto borrowingDto) throws Exception {
        BorrowingEntity entity = getBorrowingEntity(borrowingDto);
        return BorrowingDao.update(entity) ? "Success" : "Fail";
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
    public ArrayList<BorrowingDto> getAll() throws Exception {
        ArrayList<BorrowingEntity> BorrowingEntities = BorrowingDao.getAll();
        if (BorrowingEntities != null && !BorrowingEntities.isEmpty()) {
            ArrayList<BorrowingDto> borrowingDtos = new ArrayList<>();

            for (BorrowingEntity BorrowingEntity : BorrowingEntities) {
                borrowingDtos.add(getBorrowingDto(BorrowingEntity));
            }

            return borrowingDtos;
        }
        return null;
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
}
