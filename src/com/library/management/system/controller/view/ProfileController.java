package com.library.management.system.controller.view;

import java.sql.Date;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.UserController;
import com.library.management.system.controller.view.tm.BorrowingTm;
import com.library.management.system.dto.BorrowingDto;
import com.library.management.system.dto.UserDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ProfileController {
    @FXML
    private TableView<BorrowingTm> tblborrowings;

    @FXML
    private Label lbladmin;

    @FXML
    private Label lblhome;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtId;

    @FXML
    private Label loginerr;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtpasswordnew;

    @FXML
    private TableColumn<BorrowingTm, String> idColumn;
    @FXML
    private TableColumn<BorrowingTm, String> bookIdColumn;
    @FXML
    private TableColumn<BorrowingTm, String> bookColumn;
    @FXML
    private TableColumn<BorrowingTm, Date> borrowedDateColumn;
    @FXML
    private TableColumn<BorrowingTm, Date> returnDateColumn;
    @FXML
    private TableColumn<BorrowingTm, String> statusColumn;

    private String backPage;

    // private String thisPage = "/com/library/management/system/view/Profile.fxml";

    private Utils utils = Utils.getInstance();
    private ProfileController profileController;

    @FXML
    void backPaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToBack(backPage, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String currentPassword = txtpassword.getText();
        String newPassword = txtpasswordnew.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty()) {
            loginerr.setText("Please fill Current Password and New password !");
        } else {
            if (utils.changepassword(currentPassword, newPassword)) {

                loginerr.setText("Password changed !");
                loginerr.setStyle("-fx-text-fill: #7dff00;");
            } else {
                loginerr.setText("Invalid credintials or something went wrong !");
            }
        }
    }

    @FXML
    void homePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToHome(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
            utils.logoutUser();
            utils.switchToLogin(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profilePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToProfile(backPage, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(String backPage, String userId, String name, String email,
            ArrayList<BorrowingDto> borrowingDtos, ProfileController profileController) {
        this.backPage = backPage;
        this.profileController = profileController;
        txtUsername.setText(name);
        txtemail.setText(email);
        txtId.setText(userId);
        UserController userController = new UserController();
        try {
            UserDto userDto = userController.get(userId);
            if (userDto.getRole().equals("member")) {
                lbladmin.setVisible(false);
                lblhome.setVisible(true);
            } else {
                lblhome.setVisible(true);
                lbladmin.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblhome.setVisible(false);
            lbladmin.setVisible(false);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowedDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable(borrowingDtos);
        setupRowClickListener();
    }

    protected void loadTable(ArrayList<BorrowingDto> borrowingDtos) {

        try {
            ObservableList<BorrowingTm> borrowingTMList = FXCollections.observableArrayList();
            double rowHeight = 25;
            double headerHeight = 25;

            if (borrowingDtos != null) {
                BookController bookController = new BookController();
                borrowingTMList.clear();
                for (BorrowingDto borrowingDto : borrowingDtos) {
                    BorrowingTm borrowingTm = new BorrowingTm(
                            borrowingDto.getId(),
                            borrowingDto.getBookId(),
                            bookController.get(borrowingDto.getBookId()).getTitle(),
                            borrowingDto.getBorrowDate(),
                            borrowingDto.getReturnDate(),
                            borrowingDto.getStatus());
                    borrowingTMList.add(borrowingTm);
                }
            } else {

                borrowingTMList.clear();
                BorrowingTm borrowingTm = new BorrowingTm(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                borrowingTMList.add(borrowingTm);

            }

            tblborrowings.setItems(borrowingTMList);

            int rowCount = borrowingTMList.size();
            double totalHeight = rowCount * rowHeight + headerHeight;

            tblborrowings.setPrefHeight(totalHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRowClickListener() {
        tblborrowings.setRowFactory(new Callback<TableView<BorrowingTm>, TableRow<BorrowingTm>>() {
            @Override
            public TableRow<BorrowingTm> call(TableView<BorrowingTm> tableView) {
                final TableRow<BorrowingTm> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && !row.isEmpty()) {
                        BorrowingTm rowData = row.getItem();
                        String status = rowData.getStatus();
                        if (!status.equals("Lost") && !status
                                .equals("Returned")) {
                            utils.showBorrowedOptionsPopup(rowData, profileController);
                        }
                    }
                });
                return row;
            }
        });
    }

}
