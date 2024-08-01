package com.library.management.system.controller.view;

import java.io.IOException;
import java.util.ArrayList;

import com.library.management.system.controller.CategoryController;
import com.library.management.system.controller.view.tm.CategoriesTm;
import com.library.management.system.dto.CategoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class AdminCategoriesController {

    @FXML
    private TableColumn<CategoriesTm, String> columnCategoryId;

    @FXML
    private TableColumn<CategoriesTm, String> columnCategory;

    @FXML
    private TableView<CategoriesTm> tblCategories;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtCategoryId;

    private Utils utils = Utils.getInstance();

    private boolean txtCategoryIdEditable = true;

    private String thisPage = "/com/library/management/system/view/AdminCategories.fxml";

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
            CategoryController categoryController = new CategoryController();
            ArrayList<CategoryDto> categoryDtos = categoryController.getAll();
            ArrayList<String> categoryIds = new ArrayList<String>();
            for (CategoryDto categoryDto : categoryDtos) {
                categoryIds.add(categoryDto.getId());
            }
            txtCategoryId.setText("c" + utils.findNextId(categoryIds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteCategoryOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtCategoryId.getText();
            if (!id.isEmpty()) {

                CategoryController categoryController = new CategoryController();
                CategoryDto categoryDto = categoryController.get(id);
                if (categoryDto != null) {
                    if (categoryController.delete(categoryDto.getId()).equals("Success")) {
                        utils.showAlert("Success",
                                "The book has been successfully deleted.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't delete the Category.",
                                "Oops!");
                    }
                } else {
                    utils.showAlert("Error",
                            "Something went wrong. Couldn't find the Category to delete, or make sure to check the ID again.",
                            "Oops!");

                }
            } else {
                utils.showAlert("Error",
                        "Please make sure to fill in the ID field to delete a Category.",
                        "Oops!");

            }
        } catch (Exception e) {
            e.printStackTrace();
            utils.showAlert("Error",
                    "Something went wrong. Couldn't delete the Category.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", true);
            loadTable();
        }
    }

    @FXML
    void btnSaveCategoryOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtCategoryId.getText();
            String name = txtCategory.getText();

            if (!id.isEmpty() && !name.isEmpty()) {
                CategoryDto categoryDto = new CategoryDto(id, name);

                CategoryController categoryController = new CategoryController();
                CategoryDto categoryDto2 = categoryController.get(id);

                if (categoryDto2 != null) {
                    utils.showAlert("Error",
                            "Use the 'Update Category' button to update the Category, or make sure to check the ID again.",
                            "Oops!");

                } else {
                    if (categoryController.save(categoryDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The Category has been successfully saved.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't save the Category.",
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
                    "Something went wrong. Couldn't save the Category.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", true);
            loadTable();
        }
    }

    @FXML
    void btnUpdateCategoryOnAction(ActionEvent event) throws IOException {
        try {
            String id = txtCategoryId.getText();
            String name = txtCategory.getText();

            if (!id.isEmpty() && !name.isEmpty()) {
                CategoryDto categoryDto = new CategoryDto(id, name);

                CategoryController categoryController = new CategoryController();
                CategoryDto categoryDto2 = categoryController.get(id);
                if (categoryDto2 == null) {
                    utils.showAlert("Error",
                            "Use the 'Save Category' button to add a new Category, or make sure to check the ID again.",
                            "Oops!");

                } else {
                    if (categoryController.update(categoryDto).equals("Success")) {
                        utils.showAlert("Success",
                                "The Category has been successfully updated.",
                                "Excellent!");
                    } else {
                        utils.showAlert("Error",
                                "Something went wrong. Couldn't update the Category.",
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
                    "Something went wrong. Couldn't update the Category.",
                    "Oops!");
        } finally {
            setTextFeildValues("", "", true);
            loadTable();
        }
    }

    @FXML
    void txtCategoryIdonchanged() {
        try {
            if (txtCategoryIdEditable) {
                String categoryId = txtCategoryId.getText();
                if (!categoryId.isEmpty()) {
                    CategoryController categoryController = new CategoryController();
                    CategoryDto categoryDto = categoryController.get(categoryId);
                    if (categoryDto != null) {
                        setTextFeildValues(categoryDto.getId(), categoryDto.getName(), true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {

            columnCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
            columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

            loadTable();
            setupRowClickListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTable() {
        try {

            CategoryController categoryController = new CategoryController();
            ArrayList<CategoryDto> categoryDtos = categoryController.getAll();
            ObservableList<CategoriesTm> categoriesTms = FXCollections.observableArrayList();
            double rowHeight = 25;
            double headerHeight = 25;
            categoriesTms.clear();

            for (CategoryDto categoryDto : categoryDtos) {

                CategoriesTm categoriesTm = new CategoriesTm(
                        categoryDto.getId(), categoryDto.getName());
                categoriesTms.add(categoriesTm);

            }

            tblCategories.setItems(categoriesTms);

            int rowCount = categoriesTms.size();
            double totalHeight = rowCount * rowHeight + headerHeight;

            tblCategories.setPrefHeight(totalHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRowClickListener() {
        tblCategories.setRowFactory(new Callback<TableView<CategoriesTm>, TableRow<CategoriesTm>>() {

            @Override
            public TableRow<CategoriesTm> call(TableView<CategoriesTm> tableView) {
                final TableRow<CategoriesTm> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && !row.isEmpty()) {
                        CategoriesTm rowData = row.getItem();

                        setTextFeildValues(rowData.getCategoryId(), rowData.getCategory(), true);
                    }
                });
                return row;
            }

        });
    }

    private void setTextFeildValues(String categoryId, String category, boolean txtCategoryIdEditable) {

        txtCategoryId.setEditable(txtCategoryIdEditable);
        this.txtCategoryIdEditable = txtCategoryIdEditable;

        txtCategoryId.setText(categoryId);
        txtCategory.setText(category);
    }

}
