/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.controller;

import com.library.management.system.dto.CategoryDto;
import com.library.management.system.service.ServiceFactory;
import com.library.management.system.service.custom.CategoryService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class CategoryController {
    private final CategoryService categoryService = (CategoryService) ServiceFactory.getInstance()
            .getService(ServiceFactory.ServiceTypes.CATEGORY);

    public String save(CategoryDto categoryDto) throws Exception {
        return categoryService.save(categoryDto);
    }

    public String update(CategoryDto CategoryDto) throws Exception {
        return categoryService.update(CategoryDto);
    }

    public String delete(String CategoryId) throws Exception {
        return categoryService.delete(CategoryId);
    }

    public int count() throws Exception {
        return categoryService.count();
    }

    public ArrayList<CategoryDto> getAll() throws Exception {
        return categoryService.getAll();
    }

    public CategoryDto get(String CategoryId) throws Exception {
        return categoryService.get(CategoryId);
    }
}
