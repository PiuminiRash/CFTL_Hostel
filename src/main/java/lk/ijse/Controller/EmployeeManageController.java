package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.JobTypeDto;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.JobTypeModel;

import java.sql.SQLException;
import java.util.List;
import org.controlsfx.control.Notifications;
import java.util.regex.Pattern;


public class EmployeeManageController {
    @FXML
    private JFXComboBox<String> cmbBranchId;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbJobType;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private Label lblBranchName;

    @FXML
    private Label lblJobType;

    @FXML
    private AnchorPane root;

    private EmployeeModel employeeModel = new EmployeeModel();

    private JobTypeModel jobTypeModel = new JobTypeModel();

    public void initialize() {
        loadJobType();
        generateNextEmployeeId();
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

    private void generateNextEmployeeId() {
        try {
            String orderId = employeeModel.generateNextEmployeeId();
            txtEmployeeId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        String jobType = cmbJobType.getValue();
        String NIC = txtNIC.getText();

            try {
                if (!validateEmployee()){
                    return;
                }
                var dto = new EmployeeDto(id,name,address,jobType,NIC);
                boolean isSaved = EmployeeModel.saveEmployee(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            new Alert(Alert.AlertType.INFORMATION, "ID no not validate");
    }

    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtEmployeeName.getText());
        if (!name){
            showErrorNotification("Invalid Employee Name", "The Employee name you entered is invalid");
            isValidate = false;
        }
        boolean address = Pattern.matches("^(.+)@(.+)$",txtAddress.getText());
        if(!address){
            showErrorNotification("invalid address","The address you entered is invalid");
            isValidate = false;
        }
        boolean NIC = Pattern.matches("^([0-9]{9}|[0-9]{12})$",txtNIC.getText());
        if (!NIC){
            showErrorNotification("Invalid NIC", "The NIC Number you entered is invalid");
            isValidate = false;
        }
        return isValidate;
    }

    private void showErrorNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showError();

    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtAddress.setText("");
        cmbJobType.setValue("");
        txtNIC.setText("");
        lblBranchName.setText("");
        lblJobType.setText("");

        generateNextEmployeeId();
    }

    public void cmbJobTypeOnAction(ActionEvent actionEvent) {
        String id = cmbJobType.getValue();

        try {
            JobTypeDto jobTypeDto = jobTypeModel.searchJobType(id);
            lblJobType.setText(jobTypeDto.getJobType());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
