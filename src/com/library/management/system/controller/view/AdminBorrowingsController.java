package com.library.management.system.controller.view;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.BorrowingController;
import com.library.management.system.controller.view.tm.BorrowingsTm;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.BorrowingDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class AdminBorrowingsController {

    @FXML
    private TableColumn<BorrowingsTm, String> idColumn;

    @FXML
    private TableColumn<BorrowingsTm, String> userIdColumn;
    @FXML
    private TableColumn<BorrowingsTm, String> bookIdColumn;
    @FXML
    private TableColumn<BorrowingsTm, String> bookColumn;
    @FXML
    private TableColumn<BorrowingsTm, Date> borrowedDateColumn;
    @FXML
    private TableColumn<BorrowingsTm, Date> returnDateColumn;
    @FXML
    private TableColumn<BorrowingsTm, String> statusColumn;

    @FXML
    private TableView<BorrowingsTm> tblborrowings;

    @FXML
    private TextField txtBook;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtBookId;

    @FXML
    private DatePicker txtBorrowDate;

    @FXML
    private TextField txtId;

    @FXML
    private DatePicker txtReturnDate;

    @FXML
    private TextField txtStatus;

    private String thisPage = "/com/library/management/system/view/AdminBorrowings.fxml";

    @FXML
    void adminPaneOnMouseClick(MouseEvent event) {
        utils.goToAdminPage("dashboard", event);
    }

    @FXML
    void profilePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToProfile(thisPage, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            if (!id.isEmpty()) {

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto = borrowingController.get(id);
                if (borrowingDto != null) {
                    if (borrowingController.delete(borrowingDto.getId()).equals("Success")) {
                        utils.showAlert("Success",
                                "The Borrowing has been successfully deleted.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't delete the Borrowing.",
                                "Oops!");
                    }
                } else {
                    utils.showAlert("Error",
                            "Something went wrong. Couldn't find the Borrowing to delete, or make sure to check the ID again.",
                            "Oops!");

                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill in the ID field to delete a Borrowing.",
                        "Oops!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't delete the Borrowing.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", "", null, null, "", true);
            loadTable();
        }
    }

    @FXML
    void btnGenerateIdOnclick(ActionEvent event) {
        try {
            BorrowingController borrowingController = new BorrowingController();
            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getAll();
            ArrayList<String> borrowingIds = new ArrayList<String>();
            for (BorrowingDto borrowingDto : borrowingDtos) {
                borrowingIds.add(borrowingDto.getId());
            }
            txtId.setText("br" + utils.findNextId(borrowingIds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveUserOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            String userId = txtUserId.getText();
            String bookId = txtBookId.getText();
            LocalDate localDate = txtBorrowDate.getValue();
            Date borrowDate = localDate != null ? Date.valueOf(localDate) : null;
            LocalDate localDate1 = txtReturnDate.getValue();
            Date returnDate = localDate1 != null ? Date.valueOf(localDate1) : null;
            String status = txtStatus.getText();

            if (!id.isEmpty() && !userId.isEmpty() && !bookId.isEmpty() && !status.isEmpty()
                    && borrowDate != null & returnDate != null) {
                BorrowingDto borrowingDto = new BorrowingDto(id, userId, bookId, borrowDate, returnDate, status);

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto2 = borrowingController.get(id);

                if (borrowingDto2 != null) {
                    utils.showAlert("Error",
                            "Use the 'Update Borrowing' button to update the Borrowing, or make sure to check the ID again.",
                            "Oops!");

                } else {
                    if (borrowingController.save(borrowingDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The Borrowing has been successfully saved.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't save the Borrowing.",
                                "Oops!");
                    }
                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill all feilds.",
                        "Oops!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't save the Borrowing.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", "", null, null, "", true);
            loadTable();
        }
    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            String userId = txtUserId.getText();
            String bookId = txtBookId.getText();
            LocalDate localDate = txtBorrowDate.getValue();
            Date borrowDate = localDate != null ? Date.valueOf(localDate) : null;
            LocalDate localDate1 = txtReturnDate.getValue();
            Date returnDate = localDate1 != null ? Date.valueOf(localDate1) : null;
            String status = txtStatus.getText();

            if (!id.isEmpty() && !userId.isEmpty() && !bookId.isEmpty() && !status.isEmpty()
                    && borrowDate != null & returnDate != null) {

                BorrowingController borrowingController = new BorrowingController();
                BorrowingDto borrowingDto = borrowingController.get(id);
                if (borrowingDto == null) {
                    utils.showAlert("Error",
                            "Use the 'Save Borrowing' button to add a new Borrowing, or make sure to check the ID again.",
                            "Oops!");

                } else {
                    borrowingDto.setUserId(userId);
                    borrowingDto.setBookId(bookId);
                    borrowingDto.setBorrowDate(borrowDate);
                    borrowingDto.setReturnDate(returnDate);
                    borrowingDto.setStatus(status);
                    if (borrowingController.update(borrowingDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The Borrowing has been successfully updated.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't update the Borrowing.",
                                "Oops!");
                    }
                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill in all fields.",
                        "Oops!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't update the Borrowing.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", "", null, null, "", true);
            loadTable();
        }
    }

    @FXML
    void txtIdonchanged(KeyEvent event) {
        try {
            if (txtIdEditable) {
                String Id = txtId.getText();
                if (!Id.isEmpty()) {
                    BorrowingController borrowingController = new BorrowingController();
                    BorrowingDto borrowingDto = borrowingController.get(Id);
                    if (borrowingDto != null) {
                        setTextFeildValues(borrowingDto.getId(), borrowingDto.getUserId(), borrowingDto.getBookId(),
                                borrowingDto.getBorrowDate(), borrowingDto.getReturnDate(), borrowingDto.getStatus(),
                                true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookIdOnChange(KeyEvent event) {
        try {

            String Id = txtBookId.getText();
            if (!Id.isEmpty()) {
                BookController bookController = new BookController();
                BookDto bookDto = bookController.get(Id);
                if (bookDto != null) {
                    txtBook.setText(bookDto.getTitle());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean txtIdEditable = true;
    private Utils utils = Utils.getInstance();

    public void initialize() {
        try {

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
            bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
            borrowedDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            loadTable();
            setupRowClickListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRowClickListener() {
        tblborrowings.setRowFactory(new Callback<TableView<BorrowingsTm>, TableRow<BorrowingsTm>>() {
            @Override
            public TableRow<BorrowingsTm> call(TableView<BorrowingsTm> tableView) {
                final TableRow<BorrowingsTm> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && !row.isEmpty()) {
                        BorrowingsTm rowData = row.getItem();

                        setTextFeildValues(rowData.getId(), rowData.getUserId(), rowData.getBookId(),
                                rowData.getBorrowDate(),
                                rowData.getReturnDate(), rowData.getStatus(), true);

                    }
                });
                return row;
            }

        });
    }

    private void loadTable() {

        try {
            BookController bookController = new BookController();
            BorrowingController borrowingController = new BorrowingController();

            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getAll();
            ObservableList<BorrowingsTm> borrowingTMList = FXCollections.observableArrayList();
            double rowHeight = 25;
            double headerHeight = 25;

            borrowingTMList.clear();

            for (BorrowingDto borrowingDto : borrowingDtos) {

                BorrowingsTm borrowingTm = new BorrowingsTm(
                        borrowingDto.getId(),
                        borrowingDto.getUserId(),
                        borrowingDto.getBookId(),
                        bookController.get(borrowingDto.getBookId()).getTitle(),
                        borrowingDto.getBorrowDate(),
                        borrowingDto.getReturnDate(),
                        borrowingDto.getStatus());
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

    private void setTextFeildValues(String id, String userId, String bookId, Date borrowDate, Date returnDate,
            String status,
            boolean txtIdEditable) {
        try {
            this.txtIdEditable = txtIdEditable;
            txtId.setText(id);
            txtUserId.setText(userId);
            txtBookId.setText(bookId);

            BookController bookController = new BookController();
            BookDto bookDto = bookController.get(bookId);

            txtBook.setText(bookDto != null ? bookDto.getTitle() : "");

            if (borrowDate != null) {
                txtBorrowDate.setValue(borrowDate.toLocalDate());
            } else {
                txtBorrowDate.setValue(null);
            }

            if (returnDate != null) {
                txtReturnDate.setValue(returnDate.toLocalDate());
            } else {
                txtReturnDate.setValue(null);
            }
            txtStatus.setText(status);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
