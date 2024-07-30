package com.library.management.system.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowBookPopupController {

    @FXML
    private Label lblPopupTitle;

    @FXML
    private Label lblReturnDate;

    @FXML
    private Label lblFine;

    private Stage popupStage;
    private String bookId;

    private Utils utils = Utils.getInstance();

    public void setPopupStage(Stage stage) {
        this.popupStage = stage;
    }

    public void setDetails(String title, String bookId, LocalDate returnDate) {
        lblPopupTitle.setText(title);
        this.bookId = bookId;
        lblReturnDate.setText(returnDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lblFine.setText("Rs. " + utils.getFinePerDay() + " per day after due date");
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        try {
            if (utils.borrowBook(bookId).equals("Success")) {

                utils.showAlert("Success", "Book successfully borrowed", "Excellent !");

            } else {
                utils.showAlert("Error", "Something went wrong. Book cannot be borrowed", "Ooops !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popupStage.close();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        popupStage.close();
    }
}
