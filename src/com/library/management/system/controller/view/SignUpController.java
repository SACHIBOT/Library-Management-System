package com.library.management.system.controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SignUpController {

    @FXML
    private Pane bgpane;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtemail;

    @FXML
    private Label loginerr;

    @FXML
    private TextField txtpassword;

    private Utils utils = Utils.getInstance();

    @FXML
    void btnSignuponaction(ActionEvent event) {
        String username = txtUsername.getText();
        String email = txtemail.getText();
        String password = txtpassword.getText();
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            loginerr.setText("Please fill username , email and password !");
        } else {
            utils.signupUser(username, password, email, event, loginerr);

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
    void lblHaveanAcconMouseClick(MouseEvent event) {
        try {
            utils.switchToLogin(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

        utils.setBackgroundImagetoPane(bgpane,
                "com/library/management/system/view/images/mainpagebg.png");
    }
}
