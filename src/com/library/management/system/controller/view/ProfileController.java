package com.library.management.system.controller.view;

import java.sql.Date;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.view.tm.BorrowingTm;
import com.library.management.system.dto.BorrowingDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ProfileController {
    @FXML
    private TableView<BorrowingTm> tblborrowings;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtpassword1;

    @FXML
    private TableColumn<BorrowingTm, Long> idColumn;
    @FXML
    private TableColumn<BorrowingTm, Long> bookIdColumn;
    @FXML
    private TableColumn<BorrowingTm, String> bookColumn;
    @FXML
    private TableColumn<BorrowingTm, Date> borrowedDateColumn;
    @FXML
    private TableColumn<BorrowingTm, Date> returnDateColumn;
    @FXML
    private TableColumn<BorrowingTm, String> statusColumn;

    @FXML
    void backPaneOnMouseClick(MouseEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void homePaneOnMouseClick(MouseEvent event) {

    }

    @FXML
    void profilePaneOnMouseClick(MouseEvent event) {

    }

    public void initialize(String name, String email, ArrayList<BorrowingDto> borrowingDtos) {
        txtUsername.setText(name);
        txtemail.setText(email);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowedDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadTable(borrowingDtos);
    }

    public void loadTable(ArrayList<BorrowingDto> borrowingDtos) {
        BookController bookController = new BookController();
        ObservableList<BorrowingTm> borrowingTMList = FXCollections.observableArrayList();
        try {
            for (BorrowingDto borrowingDto : borrowingDtos) {
                BorrowingTm borrowingTm;

                borrowingTm = new BorrowingTm(borrowingDto.getId(),
                        borrowingDto.getBookId(),
                        bookController.get(borrowingDto.getBookId()).getTitle(),
                        (Date) borrowingDto.getBorrowDate(),
                        (Date) borrowingDto.getReturnDate(), borrowingDto.getStatus());

                borrowingTMList.add(borrowingTm);
            }
            tblborrowings.setItems(borrowingTMList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
