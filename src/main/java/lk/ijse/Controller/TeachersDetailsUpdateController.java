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
import lk.ijse.dto.TeacherDto;
import lk.ijse.model.TeacherModel;

import java.sql.SQLException;
import java.util.List;

public class TeachersDetailsUpdateController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXComboBox<String> cmbTeacherId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNic;

    private TeacherModel teacherModel = new TeacherModel();

    public void initialize(){
        loadAllTeacher();
    }

    private void loadAllTeacher() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<TeacherDto> idList = teacherModel.loadAllTeacher();

            for ( TeacherDto dto : idList) {
                obList.add(dto.getTeacherId());
            }
            cmbTeacherId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void cmbTeacherIdOnAction(ActionEvent actionEvent) {
        String id = cmbTeacherId.getValue();

        try{
            TeacherDto dto = teacherModel.searchTeacher(id);
            txtName.setText(dto.getTeacherName());
            txtAddress.setText(dto.getAddress());
            txtNic.setText(dto.getNIC());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id =  cmbTeacherId.getValue();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();

        try{

            var dto = new TeacherDto(id,name,address,nic);
            boolean isUpdate = teacherModel.updateTeacher(dto);

            if (isUpdate){
               new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
