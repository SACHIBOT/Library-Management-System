/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.entity;

/**
 *
 * @author Lenovo
 */
public class BookEntity {
    private String id;
    private String title;
    private String author;
    private String categoryId;
    private int copiesQoH;
    private String imagePath;

    public BookEntity() {
    }

    public BookEntity(String id, String title, String author, String categoryId, int copiesQoH, String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.copiesQoH = copiesQoH;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getCopiesQoH() {
        return copiesQoH;
    }

    public void setCopiesQoH(int copiesQoH) {
        this.copiesQoH = copiesQoH;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean getIsAvailable() {
        return this.copiesQoH > 0;
    }

    @Override
    public String toString() {
        return "BookEntity [id=" + id + ", title=" + title + ", author=" + author + ", categoryId=" + categoryId
                + ", copiesQoH=" + copiesQoH + ", imagePath="
                + imagePath + "]";
    }

}
