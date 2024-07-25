package com.library.management.system.controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.BorrowingController;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.BorrowingDto;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void initialize() {
        try {
            setPaneBackgroundImage(paneHelloReader, "com/library/management/system/view/images/mainpage-bg-hero.png");
            BorrowingController borrowingController = new BorrowingController();
            BookController bookController = new BookController();

            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getTop5byBookId();
            for (int i = 0; i < borrowingDtos.size(); i++) {
                BorrowingDto borrowingDto = borrowingDtos.get(i);
                BookDto bookDto = bookController.get(borrowingDto.getBookId());
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
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOickaBookOnAction(ActionEvent event) {
        try {
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void top1OnMouseClick(MouseEvent event) {
        try {
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void top2OnMouseClick(MouseEvent event) {
        try {
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void top3OnMouseClick(MouseEvent event) {
        try {
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void top4OnMouseClick(MouseEvent event) {
        try {
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void top5OnMouseClick(MouseEvent event) {
        try {
            switchToAnotherPage("/com/library/management/system/view/Books.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTopBooksDetails(Pane topPane, Label titleLabel, String title, Label topLabel, int topValue,
            Pane imagePane, String imagePath) {
        titleLabel.setText(title);
        topLabel.setText("# Top " + topValue);
        topPane.setStyle("-fx-background-color: #304463; -fx-background-radius:5; -fx-border-radius:5;");

        ImageView imageView = new ImageView(
                new Image(getClass().getResource("/com/library/management/system/view/" + imagePath).toExternalForm()));
        imageView.setFitWidth(imagePane.getPrefWidth());
        imageView.setFitHeight(imagePane.getPrefHeight());
        imageView.setPreserveRatio(true);

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

        imagePane.getChildren().clear();
        imagePane.getChildren().add(imageView);
    }

    private void setPaneBackgroundImage(Pane pane, String imagePath) {
        String image = getClass().getResource("/" + imagePath).toExternalForm();
        pane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; ");
    }

    private void switchToAnotherPage(String newpage, Event event) throws IOException {
        URL resource = getClass().getResource(newpage);
        if (resource == null) {
            throw new IOException("Resource not found: " + newpage);
        }
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
