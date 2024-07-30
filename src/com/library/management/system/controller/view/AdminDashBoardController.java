package com.library.management.system.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class AdminDashBoardController {

    @FXML
    private Label lblOvdBooks;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private Label lblTotalMembers;

    @FXML
    private Label lblTotalCategories;

    @FXML
    private TextField searchBar;

    @FXML
    void adminPaneOnMouseClick(MouseEvent event) {

    }

    @FXML
    void backPaneOnMouseClick(MouseEvent event) {

    }

    @FXML
    void btnAddNewBorrowingOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnBorrowingsOnAction(ActionEvent event) {

    }

    @FXML
    void btnCategoriesOnAction(ActionEvent event) {

    }

    @FXML
    void btnMembersOnAction(ActionEvent event) {

    }

    @FXML
    void homePaneOnMouseClick(MouseEvent event) {

    }

    @FXML
    void profilePaneOnMouseClick(MouseEvent event) {

    }

    @FXML
    void registerNewAccOnAction(ActionEvent event) {

    }

    @FXML
    void typingOnAction(InputMethodEvent event) {

    }

    public void setData(int overDueBooks, int totalBooks, int totalMembers, int totalCategories) {
        lblOvdBooks.setText(String.valueOf(overDueBooks));
        lblTotalBooks.setText(String.valueOf(totalBooks));
        lblTotalMembers.setText(String.valueOf(totalMembers));
        lblTotalCategories.setText(String.valueOf(totalCategories));
    }

}
