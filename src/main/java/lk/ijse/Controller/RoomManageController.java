package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.RoomDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.model.RoomModel;
import lk.ijse.model.StudentModel;

import java.sql.SQLException;
import java.util.List;

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

    private RoomModel roomModel = new RoomModel();

    private StudentModel studentModel = new StudentModel();

    private ObservableList<String> obList = FXCollections.observableArrayList();

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    public void loadRoom(String roomNo){
        try {
            RoomDto roomDto = roomModel.searchRoom(roomNo);
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
