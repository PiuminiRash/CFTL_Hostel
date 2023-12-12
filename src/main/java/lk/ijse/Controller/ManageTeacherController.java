package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.TeacherDto;
import lk.ijse.model.TeacherModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class ManageTeacherController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXTextField txtTeacherName;

    @FXML
    private JFXTextField txtTeacherId;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtNIC;

    private TeacherModel teacherModel = new TeacherModel();

    public void initialize() {
        generateNextTeacher();
    }

    private void generateNextTeacher() {
        try {
            String teacherId = teacherModel.generateNextTeacherId();
            txtTeacherId.setText(teacherId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtTeacherId.getText();
        String name = txtTeacherName.getText();
        String address = txtAddress.getText();
        String nic = txtNIC.getText();


        try {
            if (!validateTeacher()){
                return;
            }
            var dto = new TeacherDto(id,name,address,nic);

            boolean isSaved = TeacherModel.saveTeacher(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        generateNextTeacher();
    }
    private boolean validateTeacher() {

        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtTeacherName.getText());
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


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtTeacherId.getText();
        String name = txtTeacherName.getText();
        String address = txtAddress.getText();
        String nic = txtNIC.getText();

        var dto = new TeacherDto(id, name, address, nic);

        try {
            boolean isUpdated = teacherModel.updateTeacher(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "teacher updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtTeacherId.getText();

        try {
            boolean isDeleted = teacherModel.deleteTeacher(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields(){
        txtTeacherId.setText("");
        txtTeacherName.setText("");
        txtAddress.setText("");
        txtNIC.setText("");
    }
}
