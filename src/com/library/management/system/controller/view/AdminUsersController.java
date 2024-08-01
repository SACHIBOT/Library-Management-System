package com.library.management.system.controller.view;

import java.io.IOException;
import java.util.ArrayList;

import com.library.management.system.controller.UserController;
import com.library.management.system.controller.view.tm.UsersTm;
import com.library.management.system.dto.UserDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class AdminUsersController {

    @FXML
    private TableColumn<UsersTm, String> columnEmail;

    @FXML
    private TableColumn<UsersTm, String> columnId;

    @FXML
    private TableColumn<UsersTm, String> columnName;

    @FXML
    private TableView<UsersTm> tblUsers;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    private Utils utils = Utils.getInstance();

    private String thisPage = "/com/library/management/system/view/AdminMembers.fxml";

    private boolean txtIdEditable = true;

    @FXML
    void adminPaneOnMouseClick(MouseEvent event) {
        utils.goToAdminPage("dashboard", event);
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
    void btnGenerateIdOnclick(ActionEvent event) {
        try {
            UserController userController = new UserController();
            ArrayList<UserDto> userDtos = userController.getAll();
            ArrayList<String> userIds = new ArrayList<String>();
            for (UserDto userDto : userDtos) {
                userIds.add(userDto.getId());
            }
            txtId.setText("u" + utils.findNextId(userIds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            if (!id.isEmpty()) {

                UserController userController = new UserController();
                UserDto userDto = userController.get(id);
                if (userDto != null) {
                    if (userController.delete(userDto.getId()).equals("Success")) {
                        utils.showAlert("Success",
                                "The user has been successfully deleted.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't delete the user.",
                                "Oops!");
                    }
                } else {
                    utils.showAlert("Error",
                            "Something went wrong. Couldn't find the user to delete, or make sure to check the ID again.",
                            "Oops!");

                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill in the ID field to delete a user.",
                        "Oops!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't delete the user.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", "", "", true);
            loadTable();
        }
    }

    @FXML
    void btnSaveUserOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            if (!id.isEmpty() && !name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                UserDto userDto = new UserDto();
                userDto.setId(id);
                userDto.setName(name);
                userDto.setEmail(email);
                userDto.setPassword(password);

                UserController userController = new UserController();
                UserDto userDto2 = userController.get(id);

                if (userDto2 != null) {
                    utils.showAlert("Error",
                            "Use the 'Update User' button to update the user, or make sure to check the ID again.",
                            "Oops!");

                } else {
                    if (userController.save(userDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The user has been successfully saved.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't save the user.",
                                "Oops!");
                    }
                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill all feilds.",
                        "Oops!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't save the user.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", "", "", true);
            loadTable();
        }
    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            if (!id.isEmpty() && !name.isEmpty() && !email.isEmpty()) {

                UserController userController = new UserController();
                UserDto userDto = userController.get(id);
                if (userDto == null) {
                    utils.showAlert("Error",
                            "Use the 'Save User' button to add a new user, or make sure to check the ID again.",
                            "Oops!");

                } else {
                    userDto.setName(name);
                    userDto.setEmail(email);
                    if (!password.isEmpty()) {
                        userDto.setPassword(password);
                    }
                    if (userController.update(userDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The user has been successfully updated.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't update the user.",
                                "Oops!");
                    }
                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill in all fields, except the password field.",
                        "Oops!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't update the user.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", "", "", true);
            loadTable();
        }
    }

    @FXML
    void txtIdonchanged(KeyEvent event) {
        try {
            if (txtIdEditable) {
                String Id = txtId.getText();
                if (!Id.isEmpty()) {
                    UserController userController = new UserController();
                    UserDto userDto = userController.get(Id);
                    if (userDto != null) {
                        setTextFeildValues(userDto.getId(), userDto.getName(), userDto.getEmail(), "", true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {

            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            loadTable();
            setupRowClickListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTable() {
        try {

            UserController userController = new UserController();
            ArrayList<UserDto> userDtos = userController.getAll();
            ObservableList<UsersTm> usersTms = FXCollections.observableArrayList();
            double rowHeight = 25;
            double headerHeight = 25;
            usersTms.clear();

            for (UserDto userDto : userDtos) {

                UsersTm usersTm = new UsersTm(
                        userDto.getId(), userDto.getName(), userDto.getEmail());
                usersTms.add(usersTm);

            }

            tblUsers.setItems(usersTms);

            int rowCount = usersTms.size();
            double totalHeight = rowCount * rowHeight + headerHeight;

            tblUsers.setPrefHeight(totalHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRowClickListener() {
        tblUsers.setRowFactory(new Callback<TableView<UsersTm>, TableRow<UsersTm>>() {

            @Override
            public TableRow<UsersTm> call(TableView<UsersTm> tableView) {
                final TableRow<UsersTm> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && !row.isEmpty()) {
                        UsersTm rowData = row.getItem();

                        setTextFeildValues(rowData.getId(), rowData.getName(), rowData.getEmail(), "", true);
                    }
                });
                return row;
            }

        });
    }

    private void setTextFeildValues(String Id, String name, String email, String password, boolean txtIdEditable) {

        txtId.setEditable(txtIdEditable);
        this.txtIdEditable = txtIdEditable;

        txtId.setText(Id);
        txtName.setText(name);
        txtEmail.setText(email);
        txtPassword.setText(password);
    }
}
