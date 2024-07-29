package com.library.management.system.controller.view;

import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.CategoryDto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BooksCategoryLayerViewController {

    @FXML
    private ImageView btnprev;

    @FXML
    private ImageView btnnext;

    @FXML
    private Label lblcategory;

    @FXML
    private Label lbltitle1;

    @FXML
    private Label lbltitle2;

    @FXML
    private Label lbltitle3;

    @FXML
    private Label lbltitle4;

    @FXML
    private Label lbltitle5;

    @FXML
    private Pane paneimg1;

    @FXML
    private Pane paneimg2;

    @FXML
    private Pane paneimg3;

    @FXML
    private Pane paneimg4;

    @FXML
    private Pane paneimg5;

    @FXML
    private Pane paneBook1;

    @FXML
    private Pane paneBook2;

    @FXML
    private Pane paneBook3;

    @FXML
    private Pane paneBook4;

    @FXML
    private Pane paneBook5;

    private BooksViewController booksViewController;

    private ArrayList<String> bookIds;

    private int arrayStartValue;

    private CategoryDto categoryDto;

    private int BooksCount;

    private Utils utils = Utils.getInstance();

    public void initialize(BooksViewController actions) {
        this.booksViewController = actions;
    }

    @FXML
    void btnPrevOnMouseClick(MouseEvent event) {
        booksViewController.clickOnPrevBtn(this, arrayStartValue == 0 ? arrayStartValue : arrayStartValue - 1,
                categoryDto);
    }

    @FXML
    void btnNextOnMouseClick(MouseEvent event) {
        booksViewController.clickOnNextBtn(this,
                arrayStartValue + 5 > BooksCount ? arrayStartValue : arrayStartValue + 1, categoryDto);
    }

    public void setCategory(String category) {
        lblcategory.setText(category);
    }

    public void setBooks(ArrayList<String> titles, ArrayList<String> imagePaths, ArrayList<String> bookIds,
            int arrayStartValue, CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
        this.arrayStartValue = arrayStartValue;
        this.bookIds = bookIds;
        Label[] labels = { lbltitle1, lbltitle2, lbltitle3, lbltitle4, lbltitle5 };
        Pane[] panes = { paneimg1, paneimg2, paneimg3, paneimg4, paneimg5 };
        Pane[] bookPanes = { paneBook1, paneBook2, paneBook3, paneBook4, paneBook5 };

        for (int i = 0; i < titles.size(); i++) {
            labels[i].setText(titles.get(i));
            bookPanes[i].setStyle("-fx-background-color: #304463; -fx-background-radius:5; -fx-border-radius:5;");
            utils.addImageToPane(imagePaths.get(i), panes[i]);

            int index = i;
            bookPanes[i].setOnMouseClicked(e -> handleBookClick(index, e));
        }

    }

    public void setBooksCount(int count) {
        this.BooksCount = count;
    }

    private void handleBookClick(int index, MouseEvent event) {
        try {

            BookController bookController = new BookController();
            BookDto bookDto = bookController.get(bookIds.get(index));
            utils.goToBook(bookDto, event, utils.getBooksPage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
