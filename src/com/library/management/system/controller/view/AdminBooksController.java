package com.library.management.system.controller.view;

import java.io.IOException;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.view.tm.BooksTm;
import com.library.management.system.controller.view.tm.BorrowingTm;
import com.library.management.system.dto.BookDto;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class AdminBooksController {

    @FXML
    private TableColumn<BorrowingTm, String> columnAuthor;

    @FXML
    private TableColumn<BorrowingTm, String> columnCategoryId;

    @FXML
    private TableColumn<BorrowingTm, String> columnCopies;

    @FXML
    private TableColumn<BorrowingTm, String> columnId;

    @FXML
    private TableColumn<BorrowingTm, String> columnImage;

    @FXML
    private TableColumn<BorrowingTm, String> columnTitle;

    @FXML
    private Label lbltitle;

    @FXML
    private Pane paneBook;

    @FXML
    private Pane paneimg;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<BooksTm> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtCopies;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtImage;

    @FXML
    private TextField txtTitle;

    private Utils utils;

    private String thisPage = "/com/library/management/system/view/AdminBooks.fxml";

    @FXML
    void adminPaneOnMouseClick(MouseEvent event) {
        utils.goToAdminDashboard();
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
    void typingOnAction(InputMethodEvent event) {

    }

    @FXML
    void btnDeleteBookOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            if (!id.isEmpty()) {

                BookController bookController = new BookController();
                BookDto bookDto = bookController.get(id);
                if (bookDto != null) {
                    if (bookController.delete(bookDto.getId()).equals("Success")) {
                        utils.showAlert("Success",
                                "The book has been successfully deleted.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't delete the book.",
                                "Oops!");
                    }
                } else {
                    utils.showAlert("Error",
                            "Something went wrong. Couldn't find the book to delete.",
                            "Oops!");

                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill in the ID field to delete a book.",
                        "Oops!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't delete the book.",
                    "Oops!");
        }
    }

    @FXML
    void btnLoadImageOnAction(ActionEvent event) {
        utils.addImageToPane(txtImage.getText(), paneimg);
    }

    @FXML
    void btnSaveBookOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            String title = txtTitle.getText();
            String image = txtImage.getText();
            String author = txtAuthor.getText();
            String categoryId = txtCategoryId.getText();
            int copies;
            try {
                copies = Integer.parseInt(txtCopies.getText());
            } catch (NumberFormatException e) {
                copies = 0;
            }

            if (!id.isEmpty() && !title.isEmpty() && !image.isEmpty() && !author.isEmpty() && !categoryId.isEmpty()) {
                BookDto bookDto = new BookDto(id, title, author, categoryId, copies, image);

                BookController bookController = new BookController();
                BookDto bookDto2 = bookController.get(id);
                if (bookDto2 != null) {
                    utils.showAlert("Error",
                            "Use the 'Update Book' button to update the book.",
                            "Oops!");

                } else {
                    if (bookController.save(bookDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The book has been successfully saved.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't save the book.",
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
                    "Something went wrong. Couldn't save the book.",
                    "Oops!");
        }
    }

    @FXML
    void btnUpdateBooksOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            String title = txtTitle.getText();
            String image = txtImage.getText();
            String author = txtAuthor.getText();
            String categoryId = txtCategoryId.getText();
            int copies;
            try {
                copies = Integer.parseInt(txtCopies.getText());
            } catch (NumberFormatException e) {
                copies = 0;
            }

            if (!id.isEmpty() && !title.isEmpty() && !image.isEmpty() && !author.isEmpty() && !categoryId.isEmpty()) {
                BookDto bookDto = new BookDto(id, title, author, categoryId, copies, image);

                BookController bookController = new BookController();
                BookDto bookDto2 = bookController.get(id);
                if (bookDto2 == null) {
                    utils.showAlert("Error",
                            "Use the 'Save Book' button to add a new book.",
                            "Oops!");

                } else {
                    if (bookController.update(bookDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The book has been successfully updated.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't update the book.",
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
                    "Something went wrong. Couldn't update the book.",
                    "Oops!");
        }
    }

    private boolean txtIdEditable = true;

    @FXML
    void txtIdonchanged(KeyEvent event) {
        if (txtIdEditable) {
            String id = txtId.getText();
            if (!id.isEmpty()) {
                System.out.println(id);
            }
        }
    }

    public void initialize() {
        try {
            BookController bookController = new BookController();
            ArrayList<BookDto> bookDtos = bookController.getAll();

            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            columnCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
            columnCopies.setCellValueFactory(new PropertyValueFactory<>("copies"));
            columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));

            loadTable(bookDtos);
            setupRowClickListener();
            utils = Utils.getInstance();
            utils.addImageToPane("images/bookimages/bookimage.jpg", paneimg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTextFeildValues(String id, String title, String author, String categoryId, String copiesCount,
            String image) {
        if (!id.isEmpty()) {
            txtId.setEditable(false);
            txtIdEditable = false;
        } else {
            txtId.setEditable(true);
            txtIdEditable = true;
        }

        txtId.setText(id);
        txtTitle.setText(title);
        txtAuthor.setText(author);
        txtCategoryId.setText(categoryId);
        txtCopies.setText(copiesCount);
        txtImage.setText(image);
        utils.addImageToPane(image, paneimg);

    }

    private void setupRowClickListener() {
        tblBooks.setRowFactory(new Callback<TableView<BooksTm>, TableRow<BooksTm>>() {
            @Override
            public TableRow<BooksTm> call(TableView<BooksTm> tableView) {
                final TableRow<BooksTm> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && !row.isEmpty()) {
                        BooksTm rowData = row.getItem();

                        setTextFeildValues(rowData.getId(), rowData.getTitle(),
                                rowData.getAuthor(), rowData.getCategoryId(),
                                String.valueOf(rowData.getCopies()), rowData.getImage());
                    }
                });
                return row;
            }
        });
    }

    private void loadTable(ArrayList<BookDto> bookDtos) {
        try {

            ObservableList<BooksTm> booksTms = FXCollections.observableArrayList();
            double rowHeight = 25;
            double headerHeight = 25;
            booksTms.clear();

            for (BookDto bookDto : bookDtos) {

                BooksTm booksTm = new BooksTm(
                        bookDto.getId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getCategoryId(),
                        bookDto.getCopiesQoH(), bookDto.getImagePath());
                booksTms.add(booksTm);

            }

            tblBooks.setItems(booksTms);

            int rowCount = booksTms.size();
            double totalHeight = rowCount * rowHeight + headerHeight;

            tblBooks.setPrefHeight(totalHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
