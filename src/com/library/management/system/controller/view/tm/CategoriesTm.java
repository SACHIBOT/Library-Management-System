package com.library.management.system.controller.view.tm;

public class CategoriesTm {
    private String categoryId;
    private String category;

    public CategoriesTm(String categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
