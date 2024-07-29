/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.controller;

import com.library.management.system.dto.BorrowingDto;
import com.library.management.system.service.ServiceFactory;
import com.library.management.system.service.custom.BorrowingService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BorrowingController {
    private final BorrowingService borrowingService = (BorrowingService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceTypes.BORROWING);

    public String save(BorrowingDto borrowingDto) throws Exception {
        return borrowingService.save(borrowingDto);
    }

    public String update(BorrowingDto borrowingDto) throws Exception {
        return borrowingService.update(borrowingDto);
    }

    public String delete(String id) throws Exception {
        return borrowingService.delete(id);
    }

    public int count() throws Exception {
        return borrowingService.count();
    }

    public ArrayList<BorrowingDto> getAll() throws Exception {
        return borrowingService.getAll();
    }

    public ArrayList<BorrowingDto> getTop5byBookId() throws Exception {
        return borrowingService.getTop5byBookId();
    }

    public BorrowingDto get(String id) throws Exception {
        return borrowingService.get(id);
    }

    public ArrayList<BorrowingDto> getByBookId(String id) throws Exception {
        return borrowingService.getByBookId(id);
    }

    public ArrayList<BorrowingDto> getByUserId(String id) throws Exception {
        return borrowingService.getByUserId(id);
    }

    public ArrayList<BorrowingDto> getByStatus(String status) throws Exception {
        return borrowingService.getByStatus(status);
    }
}
