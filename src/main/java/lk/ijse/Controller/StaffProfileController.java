package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.model.StaffModel;

import java.sql.SQLException;

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

    public void loadStaff(String staffId){
        try{
            StaffDto staffDto = staffModel.searchStaff(staffId);
            cmbStaffType.setValue(staffDto.getStaffType());
            txtStaffId.setText(staffDto.getStaffId());
            txtStaffName.setText(staffDto.getStaffName());
            txtContactNo.setText(String.valueOf(staffDto.getContactNo()));
            txtNIC.setText(staffDto.getNIC());
            txtEmail.setText(staffDto.getEmail());

        } catch (SQLException e) {
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
        try {
//            if (!validateStaff()) {
//                return;
//            }
            var staffDto  = new StaffDto(type,id,name,contactNo,NIC,email);
            boolean isSaved = staffModel.updateStaff(staffDto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Staff Details Save").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
