package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.Custom.StaffBO;
import lk.ijse.dto.StaffDto;
import lk.ijse.dto.StaffModel;


import java.sql.SQLException;

public class StaffManageController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXTextField txtStaffId;

    @FXML
    private JFXTextField txtFullName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtNIC;

    StaffBO staffBO = (StaffBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STAFF);

    private StaffModel staffModel = new StaffModel();

    public void initialize() {
        cmbType.getItems().addAll("Employee", "Teacher");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String staffType = cmbType.getValue();
        String staffId = txtStaffId.getText();
        String staffName = txtFullName.getText();
        int contactNo = Integer.parseInt(txtContactNo.getText());
        String NIC = txtNIC.getText();
        String email = txtEmail.getText();

        try {
//            if (!validateStaff()) {
//                return;
//            }
            var staffDto  = new StaffDto(staffType,staffId,staffName,contactNo,NIC,email);
            boolean isSaved = staffBO.saveStaff(staffDto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Staff Details Save").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbTypeOnAction(ActionEvent actionEvent) throws SQLException {
        if (cmbType.equals("Teacher")) {
            String teacherId = staffModel.generateStaffTeacherId();
            txtStaffId.setText(teacherId);
        } else {
            String empId  = staffModel.generateStaffEmployeeId();
            txtStaffId.setText(empId);
        }
    }
}
