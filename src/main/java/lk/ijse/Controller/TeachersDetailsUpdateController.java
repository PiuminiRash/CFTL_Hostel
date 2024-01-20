package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.StaffModel;

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

    StaffModel staffModel = new StaffModel();

  //  public void initialize(){
//        loadAllTeacher();
//    }

//    private void loadAllTeacher() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//
//        try {
//            List<StaffDto> idList = staffModel.getAllStaff();
//
//            for ( StaffDto dto : idList) {
//                obList.add(dto.getStaffId());
//            }
//            cmbTeacherId.setItems(obList);
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//
//    public void cmbTeacherIdOnAction(ActionEvent actionEvent) {
//        String id = cmbTeacherId.getValue();
//
//        try{
//            StaffDto dto = staffModel.searchStaff(id);
//            txtName.setText(dto.getStaffName());
//            txtAddress.setText(dto.getEmail());
//            txtNic.setText(dto.getNIC());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        String id =  cmbTeacherId.getValue();
//        String name = txtName.getText();
//        String address = txtAddress.getText();
//        String nic = txtNic.getText();
//
////        try{
////
//////            var dto = new StaffDto(id,name,address,nic,nic);
//////            boolean isUpdate = staffModel.updateStaff(dto);
////
//////            if (isUpdate){
//////               new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
//////            }
////        }catch (SQLException e){
//////            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
////        }
//    }

}
