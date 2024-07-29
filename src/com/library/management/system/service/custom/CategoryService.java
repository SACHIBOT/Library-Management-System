/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.library.management.system.service.custom;

import com.library.management.system.dto.CategoryDto;
import com.library.management.system.service.SuperService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface CategoryService extends SuperService {

    String save(CategoryDto categoryDto) throws Exception;

    String update(CategoryDto categoryDto) throws Exception;

    String delete(String categoryId) throws Exception;

    CategoryDto get(String categoryId) throws Exception;

    int count() throws Exception;

    ArrayList<CategoryDto> getAll() throws Exception;
}
