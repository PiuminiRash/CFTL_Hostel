package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.Custom.RoomBO;
import lk.ijse.Entity.Room;
import lk.ijse.dto.RoomDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.StudentModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class RoomManageController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtRoomNo;

    @FXML
    private JFXTextField txtRoomName;

    @FXML
    private JFXComboBox<String> cmbNoOfBed;

    @FXML
    private JFXTextField txtStudentCount;

    @FXML
    private ListView<String> listStudentRoom;

    RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);

    private StudentModel studentModel = new StudentModel();

    private ObservableList<String> obList = FXCollections.observableArrayList();

    @FXML
    void btnUpdate(ActionEvent event) {
        String roomNo = txtRoomNo.getText();
        String roomName = txtRoomName.getText();
        int BedNo = Integer.parseInt(cmbNoOfBed.getValue());

        try{
            var roomDto = new RoomDto(roomNo,roomName,BedNo);
            boolean isValidate = validateRoom();
            if (isValidate) {
                boolean isUpdate = roomBO.updateRoom(roomDto);
                if (isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Update Room Details").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean validateRoom() {
        boolean isValidate = true;
        boolean roomNo = Pattern.matches("[R][\\d]{2,}", txtRoomNo.getText());
        if (!roomNo){
            showErrorNotification("Invalid Room no.", "The Room no you entered is invalid");
            isValidate = false;
        }
        boolean room_name = Pattern.matches("[A-Za-z]{5,}",txtRoomName.getText());
        if(!room_name){
            showErrorNotification("invalid room name","The room name you entered is invalid");
            isValidate = false;
        }
        boolean beds = Pattern.matches("[0-9]{1,}",cmbNoOfBed.getValue());
        if (!beds){
            showErrorNotification("Invalid count", "The beds count you entered is invalid");
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

    public void loadRoom(String roomNo){
        try {
            RoomDto roomDto = roomBO.searchRoom(roomNo);
            txtRoomNo.setText(roomNo);
            txtRoomName.setText(roomDto.getRoomName());
            cmbNoOfBed.setValue(String.valueOf(roomDto.getNoOfBed()));

            int count = studentModel.getCount(roomNo);
            txtStudentCount.setText(String.valueOf(count));

            try {
                List<StudentDto> dtoList = studentModel.searchRoomStudent(roomNo);

                for (StudentDto dto : dtoList) {
                    obList.add(
                            dto.getStudentName()
                    );
                }

                listStudentRoom.setItems(obList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
