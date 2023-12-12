package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.JobTypeDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.dto.tm.JobTypeTm;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.JobTypeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewEmployeeController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<EmployeeTm> tblEmployeeDetails;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colJobType;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private JFXComboBox<String> cmbJobType;

    @FXML
    private Label lblJobType;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNIC;

    private EmployeeModel employeeModel = new EmployeeModel();

    private ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        loadAllEmployeeDetails();
        tableListener();
        loadJobType();
    }

    private void tableListener() {
        tblEmployeeDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {

            setData(newValue);
        });
    }

    private void setData(EmployeeTm row) {
        lblEmployeeId.setText(row.getEmployeeId());
        txtName.setText(row.getEmployeeName());
        txtAddress.setText(row.getAddress());
        txtNIC.setText(row.getNIC());
        //cmbJobType.setValue(row.getJobType());
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colJobType.setCellValueFactory(new PropertyValueFactory<>("JobType"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
    }

    private void loadAllEmployeeDetails() {
        var model = new EmployeeModel();

        try {
            List<EmployeeDto> dtoList = model.getAllEmployee();

            for (EmployeeDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn,dto.getEmployeeId());
                obList.add(
                        new EmployeeTm(
                                dto.getEmployeeId(),
                                dto.getEmployeeName(),
                                dto.getAddress(),
                                dto.getJobType(),
                                dto.getNIC(),
                                deleteBtn
                        )
                );
            }
            tblEmployeeDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String employeeId) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove employee ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(employeeTm -> employeeTm.getEmployeeId().equals(employeeId));
                tblEmployeeDetails.refresh();

                DeleteEmployee(employeeId);
            }
        });
    }

    private void DeleteEmployee(String employeeId){
        try {
            boolean isDeleted = EmployeeModel.deleteEmployee(employeeId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblEmployeeDetails.refresh();
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String id = lblEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String jobType = cmbJobType.getValue();
        String NIC = txtNIC.getText();

        try {
            boolean isUpdated = EmployeeModel.updateEmployee(
                    new EmployeeDto(
                            id,
                            name,
                            address,
                            jobType,
                            NIC
                            )
            );
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        obList.clear();
        loadAllEmployeeDetails();
    }

    public void cmbJobTypeOnAction(ActionEvent actionEvent) {
        String id = cmbJobType.getValue();

        try {
            JobTypeDto jobTypeDto = JobTypeModel.searchJobType(id);
            lblJobType.setText(jobTypeDto.getJobType());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadJobType() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<JobTypeDto> jobTypeDtos = JobTypeModel.getAllJobType();

            for (JobTypeDto dto : jobTypeDtos) {
                obList.add(dto.getJobCode());
            }
            cmbJobType.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
