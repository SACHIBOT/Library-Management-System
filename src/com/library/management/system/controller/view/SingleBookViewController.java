package com.library.management.system.controller.view;

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

    // private String thisPage = "/com/library/management/system/view/Book.fxml";

    @FXML
    void backPaneOnMouseClick(MouseEvent event) {
        try {
            utils.goToBack(backPage, event);
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
