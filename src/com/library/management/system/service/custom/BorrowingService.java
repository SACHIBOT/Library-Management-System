/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.service.custom;

import com.library.management.system.dto.BorrowingDto;
import com.library.management.system.service.SuperService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface BorrowingService extends SuperService {
    String save(BorrowingDto borrowingDto) throws Exception;

    String update(BorrowingDto borrowingDto) throws Exception;

    String delete(String borrowingId) throws Exception;

    BorrowingDto get(String borrowingId) throws Exception;

    ArrayList<BorrowingDto> getAll() throws Exception;

    int count() throws Exception;

    ArrayList<BorrowingDto> getTop5byBookId() throws Exception;

    ArrayList<BorrowingDto> getByBookId(String id) throws Exception;

    ArrayList<BorrowingDto> getByUserId(String id) throws Exception;

    ArrayList<BorrowingDto> getByStatus(String status) throws Exception;
}
