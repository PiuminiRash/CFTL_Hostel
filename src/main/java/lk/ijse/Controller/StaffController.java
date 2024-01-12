package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.StaffDto;
import lk.ijse.dto.tm.StaffTm;
import lk.ijse.model.StaffModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StaffController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StaffTm> tblStaffDetails;

    @FXML
    private TableColumn<?, ?> colStaffId;

    @FXML
    private TableColumn<?, ?> colStaffName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colDelete;

    private StaffModel staffModel = new StaffModel();

    private ObservableList<StaffTm> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllStaff();

        tblStaffDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                openStaffDetails(newValue.getStaffId());
            }
        });
    }

    private void openStaffDetails(String staffId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/staffprofile_form.fxml"));
            AnchorPane studentProfilePane = loader.load();

            StaffProfileController staffProfileController = loader.getController();

            staffProfileController.loadStaff(staffId);

            root.getChildren().clear();
            root.getChildren().add(studentProfilePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory(){
        colStaffId.setCellValueFactory(new PropertyValueFactory<>("StaffId"));
        colStaffName.setCellValueFactory(new PropertyValueFactory<>("StaffName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    public void txtSearchStaffOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchStaffTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/staffmanage_form.fxml")));
    }

    private void loadAllStaff() throws SQLException {
        var model = new StaffModel();

        ObservableList<StaffTm> obList = FXCollections.observableArrayList();

        try {
            List<StaffDto> dtoList = staffModel.getAllStaff();

            for (StaffDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn, dto.getStaffId());
                obList.add(
                        new StaffTm(
                                dto.getStaffId(),
                                dto.getStaffName(),
                                dto.getEmail(),
                                deleteBtn
                        )
                );
            }
            tblStaffDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String staffId) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove student ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(staffTm -> staffTm.getStaffId().equals(staffId));
                tblStaffDetails.refresh();

                DeleteStudent(staffId);
                try {
                    loadAllStaff();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void DeleteStudent(String staffId){
        try {
            boolean isDeleted = staffModel.deleteStaff(staffId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Staff deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Staff not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblStaffDetails.refresh();
    }
}
