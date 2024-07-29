/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.service.custom.impl;

import com.library.management.system.dao.DaoFactory;
import com.library.management.system.dao.custom.CategoryDao;
import com.library.management.system.dto.CategoryDto;
import com.library.management.system.entity.CategoryEntity;
import com.library.management.system.service.custom.CategoryService;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao CategoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);

    @Override
    public String save(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getCategoryEntity(categoryDto);
        return CategoryDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getCategoryEntity(categoryDto);
        return CategoryDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String categoryId) throws Exception {
        return CategoryDao.delete(categoryId) ? "Success" : "Fail";
    }

    @Override
    public CategoryDto get(String categoryId) throws Exception {
        CategoryEntity entity = CategoryDao.get(categoryId);
        if (entity != null) {
            return getCategoryDto(entity);
        }
        return null;
    }

    @Override
    public int count() throws Exception {
        return CategoryDao.count();
    }

    @Override
    public ArrayList<CategoryDto> getAll() throws Exception {
        ArrayList<CategoryEntity> CategoryEntitys = CategoryDao.getAll();
        if (CategoryEntitys != null && !CategoryEntitys.isEmpty()) {
            ArrayList<CategoryDto> CategoryDtos = new ArrayList<>();

            for (CategoryEntity categoryEntity : CategoryEntitys) {
                CategoryDtos.add(getCategoryDto(categoryEntity));
            }

            return CategoryDtos;
        }
        return null;
    }

    private CategoryEntity getCategoryEntity(CategoryDto CategoryDto) {
        return new CategoryEntity(
                CategoryDto.getId(),
                CategoryDto.getName());
    }

    private CategoryDto getCategoryDto(CategoryEntity entity) {
        return new CategoryDto(entity.getId(),
                entity.getName());
    }
}
