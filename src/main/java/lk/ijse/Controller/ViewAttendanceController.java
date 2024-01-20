package lk.ijse.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.dto.tm.AttendanceViewTm;

import static lk.ijse.dto.AttendanceModel.getAttendance;

public class ViewAttendanceController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton btnUpdateAttendance;

    @FXML
    private TableView<AttendanceViewTm> tblviewAttendance;

    @FXML
    private TableColumn<?, ?> colTeacherId;

    @FXML
    private TableColumn<?, ?> colTeacherName;

    @FXML
    private DatePicker Date;

    public void initialize() {
        setCellValueFactory();
        handleDateSelection();
    }

    private void setCellValueFactory() {
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
    }

    private void handleDateSelection() {
        try {
            String selectedDate = Date.getValue().toString();
            List<AttendanceDto> attendanceList = getAttendance(selectedDate);

            tblviewAttendance.getItems().clear();

            tblviewAttendance.getItems().addAll((AttendanceViewTm) attendanceList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}