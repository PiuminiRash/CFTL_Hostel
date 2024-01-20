package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.Custom.StaffBO;
import lk.ijse.Entity.Staff;
import lk.ijse.dto.*;
import lk.ijse.dto.StaffModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class StaffProfileController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtStaffId;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXComboBox<String> cmbStaffType;

    @FXML
    private JFXTextField txtStaffName;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtNIC;

    private StaffModel staffModel = new StaffModel();

    StaffBO staffBO = (StaffBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STAFF);

    public void initialize() {
        cmbStaffType.getItems().addAll("Employee", "Teacher");
    }

    public void loadStaff(String staffId){
        try{
            Staff staffDto = staffBO.searchStaff(staffId);
            cmbStaffType.setValue(staffDto.getStaffType());
            txtStaffId.setText(staffDto.getStaffId());
            txtStaffName.setText(staffDto.getStaffName());
            txtContactNo.setText(String.valueOf(staffDto.getContactNo()));
            txtNIC.setText(staffDto.getNIC());
            txtEmail.setText(staffDto.getEmail());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String type =  cmbStaffType.getValue();
        String id = txtStaffId.getText();
        String name = txtStaffName.getText();
        int contactNo = Integer.parseInt(txtContactNo.getText());
        String NIC = txtNIC.getText();
        String email = txtEmail.getText();

        try{
            var staffDto = new StaffDto(type,id,name,contactNo,NIC,email);
            boolean isValidate = validateStaff();
            if (isValidate) {
                boolean isUpdate = staffBO.updateStaff(staffDto);
                if (isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Update Staff Details").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean validateStaff() {
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtStaffName.getText());
        if (!name){
            showErrorNotification("Invalid Student Name", "The Student name you entered is invalid");
            isValidate = false;
        }
        boolean email = Pattern.matches("^(.+)@(.+)$",txtEmail.getText());
        if (!email){
            showErrorNotification("Invalid email", "The email you entered is invalid");
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
}
