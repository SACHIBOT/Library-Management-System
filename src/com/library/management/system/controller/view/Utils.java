package com.library.management.system.controller.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.BorrowingController;
import com.library.management.system.controller.CategoryController;
import com.library.management.system.controller.SessionController;
import com.library.management.system.controller.UserController;
import com.library.management.system.dto.BookDto;
import com.library.management.system.dto.BorrowingDto;
import com.library.management.system.dto.SessionDto;
import com.library.management.system.dto.UserDto;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utils {

    private static Utils utils;
    private static SessionController sessionController;

    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
            sessionController = new SessionController();
        }
        return utils;
    }

    private String viewLayer = "/com/library/management/system/view/";
    private String mainPage = viewLayer + "Main.fxml";
    private String bookPage = viewLayer + "Book.fxml";
    private String booksPage = viewLayer + "Books.fxml";
    private String loginPage = viewLayer + "login.fxml";
    private String signupPage = viewLayer + "signup.fxml";
    private String profilePage = viewLayer + "Profile.fxml";
    private int finePerDay = 10;

    public int getFinePerDay() {
        return finePerDay;
    }

    public String getBooksPage() {
        return booksPage;
    }

    private FXMLLoader switchToAnotherPage(String newpage, Event event) throws IOException {
        URL resource = getClass().getResource(newpage);
        if (resource == null) {
            throw new IOException("Resource not found: " + newpage);
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return loader;
    }

    public FXMLLoader switchToAnotherPageWithAuth(String newpage, Event event) throws Exception {
        if (sessionController.getLoggedUser().getIsLoggedIn()) {

            return switchToAnotherPage(newpage, event);

        } else {
            switchToLogin(event);
            return null;
        }
    }

    public void switchToBooksPage(Event event) throws Exception {
        switchToAnotherPageWithAuth(booksPage, event);
    }

    public void switchToLogin(Event event) throws Exception {
        sessionController.logOutUser();
        switchToAnotherPage(loginPage, event);
    }

    public void switchToSignup(Event event) throws Exception {
        sessionController.logOutUser();
        switchToAnotherPage(signupPage, event);

    }

    public void addImageToPane(String imagePath, Pane pane) {

        ImageView imageView = new ImageView(
                new Image(getClass().getResource("/com/library/management/system/view/" + imagePath)
                        .toExternalForm()));
        imageView.setFitWidth(pane.getPrefWidth());
        imageView.setFitHeight(pane.getPrefHeight());
        imageView.setPreserveRatio(true);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

        pane.getChildren().clear();
        pane.getChildren().add(imageView);

    }

    public void setBackgroundImagetoPane(Pane pane, String imagePath) {
        String image = getClass().getResource("/" + imagePath).toExternalForm();
        pane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; ");
    }

    public void goToBook(BookDto bookDto, Event event, String backPage) throws Exception {

        CategoryController categoryController = new CategoryController();
        FXMLLoader loader = switchToAnotherPageWithAuth(bookPage, event);
        if (loader != null) {
            SingleBookViewController bookView = loader
                    .getController();
            bookView.setBookDetails(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getId(),
                    categoryController.get(bookDto.getCategoryId()).getName(), bookDto.getCopiesQoH(),
                    bookDto.getImagePath(), backPage);
        }
    }

    public void goToHome(Event event) throws Exception {
        switchToAnotherPage(mainPage, event);
    }

    public void goToBack(String backPage, Event event) throws Exception {
        if (backPage.equals(mainPage)) {
            switchToAnotherPage(mainPage, event);
        } else {
            switchToAnotherPageWithAuth(backPage, event);
        }
    }

    public void loginUser(String username, String password, Event event, Label loginerr) {
        UserController userController = new UserController();
        try {
            if (userController.validateUser(username, password)) {
                SessionDto sessionDto = new SessionDto(username, password);
                if (sessionController.logInUser(sessionDto)) {
                    switchToBooksPage(event);
                } else {
                    loginerr.setText("Invalid credintials !");
                }
            } else {
                loginerr.setText("Invalid credintials !");
            }

        } catch (Exception e) {
            loginerr.setText("Something went wrong !");
            e.printStackTrace();
        }
    }

    public void signupUser(String username, String password, String email, Event event, Label loginerr) {
        UserController userController = new UserController();
        try {
            UserDto userDtobyusername = userController.get(username);
            if (userDtobyusername == null) {
                UserDto userDtobyemail = userController.get(email);
                if (userDtobyemail == null) {
                    ArrayList<UserDto> userDtos = userController.getAll();
                    ArrayList<String> userIds = new ArrayList<String>();
                    for (UserDto userDto : userDtos) {
                        userIds.add(userDto.getId());
                    }
                    UserDto userDto = new UserDto("u" + findNextId(userIds), username, email, password, "member");
                    if (userController.save(userDto).equals("Success")) {
                        loginUser(username, password, event, loginerr);
                    } else {
                        loginerr.setText("Something went wrong !");
                    }
                } else {
                    loginerr.setText("Email already exists !");
                }
            } else {
                loginerr.setText("Username already exists!");
            }

        } catch (Exception e) {
            loginerr.setText("Something went wrong !");
            e.printStackTrace();
        }
    }

    private int findNextId(ArrayList<String> list) {
        int max = Integer.MIN_VALUE;
        for (String str : list) {
            int startIndex = 0;
            while (startIndex < str.length() && !Character.isDigit(str.charAt(startIndex))) {
                startIndex++;
            }
            if (startIndex < str.length()) {
                int num = Integer.parseInt(str.substring(startIndex));
                if (num > max) {
                    max = num;
                }
            }
        }
        return max + 1;
    }

    public String borrowBook(String bookId) throws Exception {
        BorrowingController borrowingController = new BorrowingController();
        BookController bookController = new BookController();
        BookDto bookDto = bookController.get(bookId);
        if (bookDto != null && bookDto.getCopiesQoH() > 0) {
            Date currentDate = new Date(System.currentTimeMillis());
            LocalDate localDate = currentDate.toLocalDate();
            LocalDate newLocalDate = localDate.plusMonths(1);
            Date returnDate = Date.valueOf(newLocalDate);
            ArrayList<BorrowingDto> borriwingDtos = borrowingController.getAll();
            ArrayList<String> borrowingIds = new ArrayList<String>();
            for (BorrowingDto borrowingDto : borriwingDtos) {
                borrowingIds.add(borrowingDto.getId());
            }
            BorrowingDto borrowingDto = new BorrowingDto("br" + findNextId(borrowingIds),
                    sessionController.getLoggedUser()
                            .getLoggedUserId(),
                    bookId, currentDate, returnDate, "Borrowed");
            if (borrowingController.save(borrowingDto).equals("Success")) {
                return "Success";
            } else {
                return "Error";
            }
        } else {
            return "Error";
        }
    }

    public void popUpwindow(Label lblTitle, Label lblBookId, String popupfxml, String pouptitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(popupfxml));
        Parent root = loader.load();

        BorrowBookPopupController controller = loader.getController();
        controller.setDetails(lblTitle.getText(), lblBookId.getText());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(pouptitle);

        controller.setPopupStage(stage);

        stage.showAndWait();
    }

    public void showAlert(String title, String value, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/library/management/system/view/PopUp.fxml"));
        Parent root = loader.load();

        PopUpController controller = loader.getController();
        controller.setDetails(title, value);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(type);
        stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/com/library/management/system/view/images/letter-l.png")));
        stage.setResizable(false);
        controller.setPopUp(stage);

        stage.showAndWait();
    }

    public void goToProfile(MouseEvent event) throws Exception {

        FXMLLoader loader = switchToAnotherPageWithAuth(profilePage, event);
        if (loader != null) {
            ProfileController profile = loader
                    .getController();
            UserController userController = new UserController();
            String userId = sessionController.getLoggedUser().getLoggedUserId();
            UserDto userDto = userController.get(userId);
            BorrowingController borrowingController = new BorrowingController();
            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getByUserId(userId);
            profile.initialize(userDto.getName(), userDto.getEmail(), borrowingDtos);
        }
    }
}
