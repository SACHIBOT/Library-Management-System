package com.library.management.system.controller.view;

import java.io.IOException;
import java.util.ArrayList;
import com.library.management.system.controller.BookController;
import com.library.management.system.controller.CategoryController;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.CategoryDto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BooksViewController {

    Utils utils = Utils.getInstance();
    @FXML
    private GridPane paneAllCategories;

    private String thisPage = "/com/library/management/system/view/Books.fxml";

    @FXML
    void homePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToHome(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOnNextBtn(BooksCategoryLayerViewController booksCategoryLayerViewController, int arrayStartValue,
            CategoryDto category) {
        addBooksToCategoryPane(booksCategoryLayerViewController, arrayStartValue, category);
    }

    public void clickOnPrevBtn(BooksCategoryLayerViewController booksCategoryLayerViewController, int arrayStartValue,
            CategoryDto category) {
        addBooksToCategoryPane(booksCategoryLayerViewController,
                arrayStartValue, category);
    }

    public void initialize() {
        BookController bookController = new BookController();
        CategoryController categoryController = new CategoryController();
        try {

            ArrayList<BookDto> bookDtos = bookController.getAll();
            ArrayList<String> categoryIds = new ArrayList<String>();
            ArrayList<CategoryDto> categories = new ArrayList<CategoryDto>();
            for (BookDto bookDto : bookDtos) {
                if (!categoryIds.contains(bookDto.getCategoryId())) {
                    categories.add(categoryController.get(bookDto.getCategoryId()));
                    categoryIds.add(bookDto.getCategoryId());
                }

            }
            for (CategoryDto category : categories) {
                addCategoryPane(category);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    void profilePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToProfile(thisPage, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCategoryPane(CategoryDto category) {
        try {
            FXMLLoader loaderBooksCategoryLayer = new FXMLLoader(
                    getClass().getResource("/com/library/management/system/view/BooksCategoryLayer.fxml"));
            Pane booksCategoryLayerPane = loaderBooksCategoryLayer.load();

            BooksCategoryLayerViewController booksCategoryLayerViewController = loaderBooksCategoryLayer
                    .getController();
            booksCategoryLayerViewController.initialize(this);
            booksCategoryLayerViewController.setCategory(category.getName());
            addBooksToCategoryPane(booksCategoryLayerViewController, 0, category);

            int nextRow = paneAllCategories.getRowCount();
            paneAllCategories.add(booksCategoryLayerPane, 0, nextRow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBooksToCategoryPane(BooksCategoryLayerViewController booksCategoryLayerViewController,
            int arrayStartValue, CategoryDto category) {
        try {
            BookController bookController = new BookController();
            ArrayList<BookDto> bookDtos = bookController.getAll();
            ArrayList<String> titles = new ArrayList<>();
            ArrayList<String> imagePaths = new ArrayList<>();
            ArrayList<String> bookIds = new ArrayList<>();

            int i = 0;
            for (int j = arrayStartValue; j < bookDtos.size(); j++) {
                if (i < 5 && bookDtos.get(j).getCategoryId().equals(category.getId())) {
                    titles.add(bookDtos.get(j).getTitle());
                    imagePaths.add(bookDtos.get(j).getImagePath());
                    bookIds.add(bookDtos.get(j).getId());
                    i++;
                }
            }
            booksCategoryLayerViewController.setBooksCount(bookDtos.size());
            booksCategoryLayerViewController.setBooks(titles, imagePaths, bookIds, arrayStartValue, category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
