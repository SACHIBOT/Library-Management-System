package com.library.management.system.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpController {

    @FXML
    private Label lblmessage;

    @FXML
    private Label lbltitle;

    private Stage popUpStage;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        popUpStage.close();
    }

    public void setPopUp(Stage popUpStage) {
        this.popUpStage = popUpStage;
    }

    public void setDetails(String title, String message) {
        lbltitle.setText(title);
        lblmessage.setText(message);
    }

}
