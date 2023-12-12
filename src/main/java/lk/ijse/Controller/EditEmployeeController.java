package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.JobTypeDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.JobTypeModel;
import javafx.scene.control.Label;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EditEmployeeController {
    public Label lblJob;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbJobType;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    private EmployeeModel employeeModel = new EmployeeModel();

    //private ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setValue();
        setValueCode();
    }

    private void setValueCode() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            java.util.List<JobTypeDto> idList = JobTypeModel.getAllJobType();

            for ( JobTypeDto dto : idList) {
                obList.add(dto.getJobCode());
            }
            cmbJobType.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setValue() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> idList = employeeModel.getAllEmployee();

            for ( EmployeeDto dto : idList) {
                obList.add(dto.getEmployeeId());
            }
            cmbEmployeeId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void cmbJobTypeOnAction(ActionEvent actionEvent) {
        String code = cmbJobType.getValue();
        try{
            JobTypeDto dto = JobTypeModel.searchJobType(code);
            lblJob.setText(dto.getJobType());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = cmbEmployeeId.getValue();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        String jobCode = cmbJobType.getValue();
        String nic = txtNIC.getText();

        try{
            if (!validateEmployee()){
                return;
            }
            var dto = new EmployeeDto(id,name,address,jobCode,nic);
            boolean isUpdate = employeeModel.updateEmployee(dto);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private boolean validateEmployee() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtEmployeeName.getText());
        if (!name){
            showErrorNotification("Invalid Employee Name", "The Employee name you entered is invalid");
            isValidate = false;
        }
        boolean address = Pattern.matches("[A-Za-z]{10,}",txtAddress.getText());
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


    public void cmbEmployeeIdOnAction(ActionEvent actionEvent) {
        String id = cmbEmployeeId.getValue();

        try {
            EmployeeDto employeeDto = employeeModel.searchEmployee(id);
            txtEmployeeName.setText(employeeDto.getEmployeeName());
            txtAddress.setText(employeeDto.getAddress());
            txtNIC.setText(employeeDto.getNIC());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

