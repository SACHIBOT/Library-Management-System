/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.library.management.system;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("view/Main.fxml");
        if (resource == null) {
            throw new RuntimeException("main FXML file not found in path");
        }
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(
                new Image(getClass().getResourceAsStream("/com/library/management/system/view/images/letter-l.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
