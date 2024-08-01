package com.library.management.system.controller.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.library.management.system.controller.BookController;
import com.library.management.system.controller.BorrowingController;
import com.library.management.system.controller.CategoryController;
import com.library.management.system.controller.SessionController;
import com.library.management.system.controller.UserController;
import com.library.management.system.controller.view.tm.BorrowingTm;
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
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Utils {

    private static Utils utils;
    private static SessionController sessionController;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    protected static Utils getInstance() {
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
    private String borrowedOpts = viewLayer + "BorrowedOptions.fxml";
    private String adminBooksPage = viewLayer + "AdminBooks.fxml";
    private String adminCategoriesPage = viewLayer + "AdminCategories.fxml";
    private String adminUsersPage = viewLayer + "AdminMembers.fxml";
    private String adminBorrowingsPage = viewLayer + "AdminBorrowings.fxml";
    private String adminDashboardPage = viewLayer + "AdminDashboard.fxml";

    private int finePerDay = 10;

    private long fineForLLostBook = 500;

    protected int getFinePerDay() {
        return finePerDay;
    }

    protected long getFineForLostBook() {
        return fineForLLostBook;
    }

    protected String getBooksPage() {
        return booksPage;
    }

    private FXMLLoader switchToAnotherPage(String newpage, Event event) throws IOException {
        URL resource = getClass().getResource(newpage);
        if (resource == null) {
            throw new IOException("Resource not found: " + newpage);
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        Node sourceNode = (Node) event.getSource();
        if (sourceNode == null || sourceNode.getScene() == null) {
            throw new IllegalStateException("Source node or scene is null.");
        }

        Stage stage = (Stage) sourceNode.getScene().getWindow();
        try {

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        return loader;
    }

    private FXMLLoader switchToAnotherPageWithAuth(String newpage, Event event) throws Exception {
        if (sessionController != null && sessionController.getLoggedUser() != null
                && sessionController.getLoggedUser().getIsLoggedIn()) {

            return switchToAnotherPage(newpage, event);

        } else {
            switchToLogin(event);
            return null;
        }
    }

    private FXMLLoader switchToAnotherPageWithAdminAuth(String newpage, Event event) throws Exception {
        if (sessionController != null && sessionController.getLoggedUser() != null
                && sessionController.getLoggedUser().getIsLoggedIn()) {
            if (isAdmin(new UserController().get(sessionController.getLoggedUser().getLoggedUserId()))) {

                return switchToAnotherPage(newpage, event);
            } else {
                switchToLogin(event);
                return null;
            }

        } else {
            switchToLogin(event);
            return null;
        }
    }

    protected void switchToBooksPage(Event event) throws Exception {
        switchToAnotherPageWithAuth(booksPage, event);
    }

    protected void switchToLogin(Event event) throws Exception {
        sessionController.logOutUser();
        switchToAnotherPage(loginPage, event);
    }

    protected void switchToSignup(Event event) throws Exception {
        sessionController.logOutUser();
        switchToAnotherPage(signupPage, event);

    }

    public void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        File file = new File(destinationFile);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (InputStream is = url.openStream(); OutputStream os = new FileOutputStream(file)) {
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        }
    }

    private Image loadImage(String imagePath) {
        Image image;
        try {
            if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
                String localPath = "./src/com/library/management/system/view/images/bookimages/"
                        + new File(imagePath).getName();
                File localFile = new File(localPath);

                if (localFile.exists()) {
                    image = new Image(
                            getClass().getResource(localPath.replace("./src/", "/")).toExternalForm(), true);
                } else {
                    image = new Image(imagePath, true);
                    executorService.submit(() -> {
                        try {
                            saveImage(imagePath, localPath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                }
            } else {
                image = new Image(
                        getClass().getResource("/com/library/management/system/view/" + imagePath).toExternalForm(),
                        true);
            }

            if (image.isError()) {
                throw new Exception("Image not found or failed to load.");
            }
        } catch (

        Exception e) {
            image = new Image(
                    getClass().getResource("/com/library/management/system/view/images/bookimages/bookimage.jpg")
                            .toExternalForm());
        }
        return image;
    }

    protected void addImageToPane(String imagePath, Pane pane) {

        ImageView imageView = new ImageView(loadImage(imagePath));
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

    protected void setBackgroundImagetoPane(Pane pane, String imagePath) {
        String image = getClass().getResource("/" + imagePath).toExternalForm();
        pane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; ");
    }

    protected void goToBook(BookDto bookDto, Event event, String backPage) throws Exception {

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

    protected void goToHome(Event event) throws Exception {
        SessionDto sessionDto = sessionController.getLoggedUser();
        UserController userController = new UserController();
        if (isAdmin(userController.get(sessionDto.getLoggedUsername()))) {
            goToAdminPage("dashboard", event);
        } else {

            switchToAnotherPage(mainPage, event);
        }
    }

    protected void goToBack(String backPage, Event event) throws Exception {
        try {
            if (backPage.equals(mainPage)) {
                switchToAnotherPage(mainPage, event);
            } else {
                switchToAnotherPageWithAuth(backPage, event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            goToHome(event);
        }

    }

    private boolean isAdmin(UserDto userDto) {
        return userDto != null ? userDto.getRole().equals("admin") : false;
    }

    protected void loginUser(String username, String password, Event event, Label loginerr) {
        UserController userController = new UserController();
        try {
            if (userController.validateUser(username, password)) {
                SessionDto sessionDto = new SessionDto(username, password);
                if (sessionController.logInUser(sessionDto)) {
                    if (isAdmin(userController.get(username))) {
                        goToAdminPage("dashboard", event);

                    } else {
                        switchToBooksPage(event);
                    }
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

    protected void signupUser(String username, String password, String email, Event event, Label loginerr) {
        UserController userController = new UserController();
        try {
            if (!username.contains(" ")) {
                UserDto userDtobyusername = userController.get(username);
                if (userDtobyusername == null) {
                    UserDto userDtobyemail = userController.get(email);
                    if (userDtobyemail == null) {
                        ArrayList<UserDto> userDtos = userController.getAll();
                        ArrayList<String> userIds = new ArrayList<String>();
                        for (UserDto userDto : userDtos) {
                            userIds.add(userDto.getId());
                        }
                        UserDto userDto = new UserDto();
                        userDto.setId("u" + findNextId(userIds));
                        userDto.setName(username);
                        userDto.setEmail(email);
                        userDto.setPassword(password);
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
            } else {
                loginerr.setText("Username should not contain spaces !");
            }

        } catch (Exception e) {
            loginerr.setText("Something went wrong !");
            e.printStackTrace();
        }
    }

    protected int findNextId(ArrayList<String> list) {
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

    protected String borrowBook(String bookId) throws Exception {
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

    protected void borrowBookPopup(Label lblTitle, Label lblBookId, String popupfxml, String pouptitle)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(popupfxml));
        Parent root = loader.load();

        BorrowBookPopupController controller = loader.getController();

        LocalDate returnDate = LocalDate.now().plusMonths(1);
        controller.setDetails(lblTitle.getText(), lblBookId.getText(), returnDate);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(pouptitle);

        controller.setPopupStage(stage);

        stage.showAndWait();
    }

    protected void showAlert(String title, String value, String header) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/library/management/system/view/PopUp.fxml"));
        Parent root = loader.load();

        PopUpController controller = loader.getController();
        controller.setDetails(title, value);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(header);
        stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/com/library/management/system/view/images/letter-l.png")));
        stage.setResizable(false);
        controller.setPopUp(stage);

        stage.showAndWait();
    }

    protected void goToProfile(String backPage, MouseEvent event) throws Exception {

        FXMLLoader loader = switchToAnotherPageWithAuth(profilePage, event);
        if (loader != null) {
            ProfileController profile = loader
                    .getController();
            UserController userController = new UserController();
            String userId = sessionController.getLoggedUser().getLoggedUserId();
            UserDto userDto = userController.get(userId);
            BorrowingController borrowingController = new BorrowingController();
            ArrayList<BorrowingDto> borrowingDtos = borrowingController.getByUserId(userId);
            profile.initialize(backPage, userDto.getId(), userDto.getName(), userDto.getEmail(), borrowingDtos,
                    profile);
        }
    }

    protected boolean changepassword(String currentPassword, String newPassword) {
        UserController userController = new UserController();
        try {
            String userId = sessionController.getLoggedUser().getLoggedUserId();
            if (userController.updatePassword(currentPassword, newPassword, userId)) {
                SessionDto sessionDto = sessionController.getLoggedUser();
                sessionDto.setLoggedPassword(newPassword);
                sessionController.updateSession(sessionDto);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    protected void showBorrowedOptionsPopup(BorrowingTm borrowingTm, ProfileController profileController) {

        try {

            String title = borrowingTm.getBookTitle();
            Date returnDate = borrowingTm.getReturnDate();
            String status = borrowingTm.getStatus();
            if ((!status.equals("Lost") && !status
                    .equals("Returned"))) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(borrowedOpts));
                Parent root = loader.load();
                long fine = calculateFine((!status.equals("Lost") && !status
                        .equals("Returned")) ? returnDate : null);
                String borrowingId = borrowingTm.getId();

                BorrowedBookOptionsPopupController controller = loader.getController();
                controller.setPopupData(title, returnDate, fine, status, borrowingId, profileController);

                Stage stage = new Stage();
                stage.setTitle("Borrowed Book Options");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));

                stage.getIcons().add(
                        new Image(
                                getClass().getResourceAsStream(
                                        "/com/library/management/system/view/images/letter-l.png")));
                stage.setResizable(false);
                controller.setDialogStage(stage);
                stage.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long calculateFine(Date returnDate) {
        long finePerDay = getFinePerDay();
        long overdueDays = calculateOverdueDays(returnDate);
        long totalFine = overdueDays * finePerDay;
        return totalFine;

    }

    private long calculateOverdueDays(Date returnDate) {
        if (returnDate == null) {
            return 0;
        }

        LocalDate returnLocalDate = returnDate.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        long daysBetween = ChronoUnit.DAYS.between(returnLocalDate, currentDate);

        if (daysBetween < 0) {
            return 0;
        }

        return daysBetween;
    }

    private boolean payCost(long cost) {
        return true;
    }

    protected boolean lostBookPay(String borrowingId) {
        try {
            BorrowingController borrowingController = new BorrowingController();

            BorrowingDto borrowingDto = borrowingController.get(borrowingId);
            if (borrowingDto != null) {
                String status = borrowingDto.getStatus();
                if (payCost(calculateFine(
                        (!status.equals("Lost") && !status
                                .equals("Returned")) ? borrowingDto.getReturnDate() : null)
                        + fineForLLostBook)) {
                    borrowingDto.setStatus("Lost");
                    if (borrowingController.update(borrowingDto).equals("Success")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean payFineAndReturnBook(String borrowingId) {
        try {
            BorrowingController borrowingController = new BorrowingController();

            BorrowingDto borrowingDto = borrowingController.get(borrowingId);
            if (borrowingDto != null) {
                String status = borrowingDto.getStatus();
                if (payCost(calculateFine(
                        (!status.equals("Lost") && !status
                                .equals("Returned")) ? borrowingDto.getReturnDate() : null))) {
                    borrowingDto.setStatus("Returned");
                    if (borrowingController.update(borrowingDto).equals("Success")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    protected boolean renewReturnDate(String borrowingId) {
        try {
            BorrowingController borrowingController = new BorrowingController();

            BorrowingDto borrowingDto = borrowingController.get(borrowingId);
            if (borrowingDto != null) {
                String status = borrowingDto.getStatus();
                if (!status.equals("Renewed")) {
                    LocalDate returnDate = borrowingDto.getReturnDate().toLocalDate();
                    LocalDate newReturnDate = returnDate.plusWeeks(2);
                    borrowingDto.setStatus("Renewed");
                    borrowingDto.setReturnDate(Date.valueOf(newReturnDate));
                    if (borrowingController.update(borrowingDto).equals("Success")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    protected boolean returnBook(String borrowingId) {
        try {
            BorrowingController borrowingController = new BorrowingController();

            BorrowingDto borrowingDto = borrowingController.get(borrowingId);
            if (borrowingDto != null) {
                String status = borrowingDto.getStatus();
                if (!status.equals("Lost") && !status
                        .equals("Returned")) {
                    borrowingDto.setStatus("Returned");
                    if (borrowingController.update(borrowingDto).equals("Success")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void logoutUser() {
        try {
            sessionController.logOutUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void goToAdminPage(String page, Event event) {
        try {
            switch (page) {
                case "dashboard":
                    switchToAnotherPageWithAdminAuth(adminDashboardPage, event);
                    break;
                case "categories":
                    switchToAnotherPageWithAdminAuth(adminCategoriesPage, event);
                    break;
                case "users":
                    switchToAnotherPageWithAdminAuth(adminUsersPage, event);
                    break;
                case "borrowings":
                    switchToAnotherPageWithAdminAuth(adminBorrowingsPage, event);
                    break;
                case "books":
                    switchToAnotherPageWithAdminAuth(adminBooksPage, event);
                    break;
                default:
                    switchToAnotherPageWithAdminAuth(adminDashboardPage, event);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
