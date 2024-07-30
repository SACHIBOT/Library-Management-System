package com.library.management.system.controller.view;

import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.BorrowingController;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.BorrowingDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainViewController {

    @FXML
    private Label lbltop1;

    @FXML
    private Label lbltop2;

    @FXML
    private Label lbltop3;

    @FXML
    private Label lbltop4;

    @FXML
    private Label lbltop5;

    @FXML
    private Label lbltopname1;

    @FXML
    private Label lbltopname2;

    @FXML
    private Label lbltopname3;

    @FXML
    private Label lbltopname4;

    @FXML
    private Label lbltopname5;

    @FXML
    private Pane paneHelloReader;

    @FXML
    private Pane panetop1;

    @FXML
    private Pane panetop2;

    @FXML
    private Pane panetop3;

    @FXML
    private Pane panetop4;

    @FXML
    private Pane panetop5;

    @FXML
    private Pane paneimgtop1;

    @FXML
    private Pane paneimgtop2;

    @FXML
    private Pane paneimgtop3;

    @FXML
    private Pane paneimgtop4;

    @FXML
    private Pane paneimgtop5;

    private String thisPage = "/com/library/management/system/view/Main.fxml";
    private ArrayList<BookDto> top5BookDtos = new ArrayList<BookDto>();

    private Utils utils = Utils.getInstance();

    public void initialize() {
        try {
            utils.setBackgroundImagetoPane(paneHelloReader,
                    "com/library/management/system/view/images/mainpagebg.png");
            BorrowingController borrowingController = new BorrowingController();
            BookController bookController = new BookController();

            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getTop5byBookId();
            for (int i = 0; i < borrowingDtos.size(); i++) {
                BorrowingDto borrowingDto = borrowingDtos.get(i);
                BookDto bookDto = bookController.get(borrowingDto.getBookId());
                top5BookDtos.add(bookDto);
                if (i == 0) {
                    setTopBooksDetails(panetop1, lbltopname1, bookDto.getTitle(), lbltop1, 1, paneimgtop1,
                            bookDto.getImagePath());
                } else if (i == 1) {
                    setTopBooksDetails(panetop2, lbltopname2, bookDto.getTitle(), lbltop2, 2, paneimgtop2,
                            bookDto.getImagePath());
                } else if (i == 2) {
                    setTopBooksDetails(panetop3, lbltopname3, bookDto.getTitle(), lbltop3, 3, paneimgtop3,
                            bookDto.getImagePath());
                } else if (i == 3) {
                    setTopBooksDetails(panetop4, lbltopname4, bookDto.getTitle(), lbltop4, 4, paneimgtop4,
                            bookDto.getImagePath());
                } else if (i == 4) {
                    setTopBooksDetails(panetop5, lbltopname5, bookDto.getTitle(), lbltop5, 5, paneimgtop5,
                            bookDto.getImagePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnMoreBooksOnAction(ActionEvent event) {
        try {
            utils.switchToBooksPage(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    void btnOickaBookOnAction(ActionEvent event) {
        try {
            utils.switchToBooksPage(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTopBooksDetails(Pane topPane, Label titleLabel, String title, Label topLabel, int topValue,
            Pane imagePane, String imagePath) {
        titleLabel.setText(title);
        topLabel.setText("# Top " + topValue);
        topPane.setStyle("-fx-background-color: #304463; -fx-background-radius:5; -fx-border-radius:5;");

        utils.addImageToPane(imagePath, imagePane);

    }

}
