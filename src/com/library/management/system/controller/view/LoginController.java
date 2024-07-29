package com.library.management.system.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class LoginController {

    @FXML
    private Pane bgpane;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label loginerr;

    @FXML
    private TextField txtUsername;

    private Utils utils = Utils.getInstance();

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginerr.setText("Please fill username and password !");
        } else {
            utils.loginUser(username, password, event, loginerr);
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
    void lblCreateNewAcconMouseClick(MouseEvent event) {
        try {
            utils.switchToSignup(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

        utils.setBackgroundImagetoPane(bgpane,
                "com/library/management/system/view/images/mainpagebg.png");
    }
}
