package com.library.management.system.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.library.management.system.controller.BorrowingController;
import com.library.management.system.dto.BorrowingDto;

public class BorrowedBookOptionsPopupController {

    @FXML
    private Label lostmsg;

    @FXML
    private Label lblPopupTitle;

    @FXML
    private Label lblReturnDate;

    @FXML
    private Label lblFine;

    @FXML
    private Label lblFineLabel;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnRenew;

    @FXML
    private Button btnReportLost;

    @FXML
    private Button btnPayFine;

    private String borrowingId;
    private Stage dialogStage;

    Utils utils = Utils.getInstance();
    private ProfileController profileController;

    @FXML
    private void btnReturnOnAction(ActionEvent event) {
        try {
            if (utils.returnBook(borrowingId)) {
                utils.showAlert("Success",
                        "The book returned successfully.",
                        "Excellent!");
            } else {
                utils.showAlert("Error",
                        "Something went wrong. The book cannot be returned.",
                        "Oops!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialogStage.close();
            try {

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto = borrowingController.get(borrowingId);
                ArrayList<BorrowingDto> borrowingDtos = borrowingController.getByUserId(borrowingDto.getUserId());
                profileController.loadTable(borrowingDtos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnRenewOnAction(ActionEvent event) {
        try {
            if (utils.renewReturnDate(borrowingId)) {
                utils.showAlert("Success",
                        "The return date has been successfully renewed.",
                        "Excellent!");
            } else {
                utils.showAlert("Error",
                        "Something went wrong. The return date cannot be renewed.",
                        "Oops!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialogStage.close();
            try {

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto = borrowingController.get(borrowingId);
                ArrayList<BorrowingDto> borrowingDtos = borrowingController.getByUserId(borrowingDto.getUserId());
                profileController.loadTable(borrowingDtos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnReportLostOnAction(ActionEvent event) {
        try {
            if (utils.lostBookPay(borrowingId)) {
                utils.showAlert("Success",
                        "The book has been successfully reported as lost and the fine has been paid.",
                        "Excellent!");
            } else {
                utils.showAlert("Error",
                        "Something went wrong. The book could not be reported as lost and the payment could not be processed.",
                        "Oops!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialogStage.close();
            try {

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto = borrowingController.get(borrowingId);
                ArrayList<BorrowingDto> borrowingDtos = borrowingController.getByUserId(borrowingDto.getUserId());
                profileController.loadTable(borrowingDtos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnPayFineOnAction(ActionEvent event) {
        try {
            if (utils.payFineAndReturnBook(borrowingId)) {
                utils.showAlert("Success",
                        "Fine has been successfully paid and the book has been successfully returned.",
                        "Excellent!");
            } else {
                utils.showAlert("Error",
                        "Something went wrong. The book could not be returned and the payment could not be processed.",
                        "Oops!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialogStage.close();
            try {

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto = borrowingController.get(borrowingId);
                ArrayList<BorrowingDto> borrowingDtos = borrowingController.getByUserId(borrowingDto.getUserId());
                profileController.loadTable(borrowingDtos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPopupData(String title, Date returnDate, long fine, String status, String borrowingId,
            ProfileController profileController) {
        this.profileController = profileController;
        lostmsg.setText(
                "If the book is lost, an additional charge of Rs. " + utils.getFineForLostBook() + " will be applied.");
        this.borrowingId = borrowingId;
        lblPopupTitle.setText(title);
        LocalDate returnLocalDate = returnDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        lblReturnDate.setText(returnLocalDate.format(formatter));
        if ("Renewed".equals(status)) {
            btnRenew.setVisible(false);
        }
        if (fine != 0) {
            lblFineLabel.setVisible(true);
            lblFine.setVisible(true);
            lblFine.setText("Rs. " + fine);
            btnPayFine.setVisible(true);
            btnReturn.setVisible(false);
            btnReportLost.setVisible(true);
        } else {
            lblFineLabel.setVisible(false);
            lblFine.setVisible(false);
            btnPayFine.setVisible(false);
            btnReturn.setVisible(true);
            btnReportLost.setVisible(true);
        }
    }
}
