package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.RoomDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.TimeTableDto;
import lk.ijse.dto.tm.RoomTm;
import lk.ijse.dto.tm.StudentTm;
import lk.ijse.dto.tm.TimeTableTm;
import lk.ijse.model.RoomModel;
import lk.ijse.model.StudentModel;
import lk.ijse.model.TeacherModel;
import lk.ijse.model.TimeTableModel;

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
    private TableView<StudentTm> tblRoomStudent;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colMove;

    private RoomModel roomModel = new RoomModel();

    private StudentModel studentModel = new StudentModel();

    private ObservableList<StudentTm> obList = FXCollections.observableArrayList();

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
