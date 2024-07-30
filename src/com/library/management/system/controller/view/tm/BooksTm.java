package com.library.management.system.controller.view.tm;

public class BooksTm {
    private String id;
    private String title;
    private String author;
    private String categoryId;
    private int copies;
    private String image;

    public BooksTm(String id, String title, String author, String categoryId, int copies, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.copies = copies;
        this.image = image;
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

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
