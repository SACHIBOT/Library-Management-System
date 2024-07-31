package com.library.management.system.controller.view;

import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.BorrowingController;
import com.library.management.system.controller.CategoryController;
import com.library.management.system.controller.UserController;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.BorrowingDto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class AdminDashBoardController {

    @FXML
    private Label lblLostBooks;

    @FXML
    private Label lblOvdBooks;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private Label lblTotalBorrowings;

    @FXML
    private Label lblTotalCategories;

    @FXML
    private Label lblTotalMembers;

    @FXML
    private Label lblAvbBooks;

    private String thisPage = "/com/library/management/system/view/AdminDashBoard.fxml";
    private Utils utils = Utils.getInstance();

    @FXML
    void btnBooksOnAction(ActionEvent event) {
        utils.goToAdminPage("books", event);
    }

    @FXML
    void btnBorrowingsOnAction(ActionEvent event) {
        utils.goToAdminPage("borrowings", event);
    }

    @FXML
    void btnCategoriesOnAction(ActionEvent event) {
        utils.goToAdminPage("categories", event);
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) {
        utils.goToAdminPage("users", event);
    }

    @FXML
    void profilePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToProfile(thisPage, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {
            UserController userController = new UserController();
            BookController bookController = new BookController();
            BorrowingController borrowingController = new BorrowingController();
            CategoryController categoryController = new CategoryController();

            int overDueBooks = borrowingController.getByStatus("Overdue").size();
            int totalLostBooks = borrowingController.getByStatus("Lost").size();
            int totalBorrowings = borrowingController.count();
            int totalMembers = userController.count();
            int totalCategories = categoryController.count();

            int AvailableBooks = 0;
            ArrayList<BookDto> bookDtos = bookController.getAll();
            for (BookDto bookDto : bookDtos) {
                AvailableBooks += bookDto.getCopiesQoH();
            }

            int booksCountOnUsersHand = 0;
            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getAll();
            for (BorrowingDto borrowingDto : borrowingDtos) {
                if (!borrowingDto.getStatus().equals("Lost")) {
                    booksCountOnUsersHand += 1;
                }
            }

            int totalBooks = AvailableBooks + booksCountOnUsersHand;

            setData(overDueBooks, totalLostBooks, totalBorrowings, totalBooks, totalMembers, totalCategories,
                    AvailableBooks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(int overDueBooks, int totalLostBooks, int totalBorrowings, int totalBooks, int totalMembers,
            int totalCategories, int AvailableBooks) {
        lblOvdBooks.setText(String.valueOf(overDueBooks));
        lblTotalBooks.setText(String.valueOf(totalBooks));
        lblTotalMembers.setText(String.valueOf(totalMembers));
        lblTotalCategories.setText(String.valueOf(totalCategories));
        lblLostBooks.setText(String.valueOf(totalLostBooks));
        lblTotalBorrowings.setText(String.valueOf(totalBorrowings));
        lblAvbBooks.setText(String.valueOf(AvailableBooks));
    }

}
