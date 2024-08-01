package com.library.management.system.controller.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SingleBookViewController {

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblCopies;

    @FXML
    private Label lblTitle;

    @FXML
    private Pane paneimg;

    private String backPage;

    private Utils utils = Utils.getInstance();

    @FXML
    void backPaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToBack(backPage, event);
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

    @FXML
    void homePaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToHome(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {
        try {
            if (Integer.parseInt(lblCopies.getText()) > 0) {
                utils.borrowBookPopup(lblTitle, lblBookId, "/com/library/management/system/view/BorrowBookPopup.fxml",
                        "Borrow this book");
            } else {
                utils.showAlert("Error", "All books are borrowed by users.", "Ooops !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBookDetails(String title, String author, String bookId, String category, int copies, String image,
            String backPage) {
        this.backPage = backPage;
        lblTitle.setText(title);
        lblAuthor.setText(author);
        lblBookId.setText(bookId);
        lblCategory.setText(category);
        lblCopies.setText(Integer.toString(copies));
        utils.addImageToPane(image, paneimg);
    }
}
