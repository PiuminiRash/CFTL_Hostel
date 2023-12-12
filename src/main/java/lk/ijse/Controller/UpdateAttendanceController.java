package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.AttendanceTm;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateAttendanceController {
    @FXML
    private AnchorPane rootNodes;

    @FXML
    private TableView<AttendanceTm> tblAttendance;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXComboBox<String> cmbId;

    @FXML
    private JFXButton btnMarkAttendance;

    @FXML
    private Label lblName;

    @FXML
    private JFXButton btnAttendanceSave;

    @FXML
    private DatePicker dateDate;

    private TeacherModel teacherModel = new TeacherModel();

    private AttendanceModel attendanceModel = new AttendanceModel();

    private ObservableList<AttendanceTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        dateDate.setPromptText(String.valueOf(LocalDate.now()));
        loadAllTeacher();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("RemoveBtn"));
    }

    public void btnMarkAttendanceOnAction(ActionEvent actionEvent) {
        String teacherId = cmbId.getValue();
        String teacherName = lblName.getText();
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn, teacherId);
        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblAttendance.getItems().size(); i++) {
                if (colId.getCellData(i).equals(teacherId)) {

                    tblAttendance.refresh();
                    return;
                }
            }
        }

        var attendanceTm = new AttendanceTm(teacherId, teacherName, btn);

        obList.add(attendanceTm);

        tblAttendance.setItems(obList);
    }

    private void setRemoveBtnAction(Button RemoveBtn, String teacherId) {
        /*Removebtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
            }
        });*/
        RemoveBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                // Remove the selected teacher from the attendance list
                obList.removeIf(attendanceTm -> attendanceTm.getTeacherId().equals(teacherId));
                tblAttendance.setItems(obList);
            }
        });
    }


    public void cmbIdOnAction(ActionEvent actionEvent) {
        String id = cmbId.getValue();

        try {
            TeacherDto dto = teacherModel.searchTeacher(id);
            lblName.setText(dto.getTeacherName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllTeacher() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TeacherDto> teacherDtos = TeacherModel.getAllTeacher();

            for (TeacherDto dto : teacherDtos) {
                obList.add(dto.getTeacherId());
            }
            cmbId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAttendanceSaveOnAction(ActionEvent actionEvent) {
       /* String date = dateDate.getPromptText();
        String id = cmbId.getValue();
        String name = lblName.getText();

        List<AttendanceTm> attendanceTmList = new ArrayList<>();
        for (int i = 0; i < tblAttendance.getItems().size(); i++) {
            AttendanceTm attendanceTm = obList.get(i);

            attendanceTmList.add(attendanceTm);
        }

        System.out.println("Attendance Details: " +  attendanceTmList);
        var attendanceDto = new AttendanceDto(date,id,name);
        try {
            boolean isSuccess = AttendanceModel.addAttendance(attendanceDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attendance Save Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
        String date = dateDate.getPromptText();

        List<AttendanceDto> attendanceDtoList = new ArrayList<>();

        for (AttendanceTm attendanceTm : obList) {
            String id = attendanceTm.getTeacherId();
            String name = attendanceTm.getTeacherName();
            boolean isPresent = attendanceTm.isPresent();


            AttendanceDto attendanceDto = new AttendanceDto(date, id, name, isPresent);
            attendanceDtoList.add(attendanceDto);
        }

        try {
            boolean isSuccess = AttendanceModel.addAttendanceList(attendanceDtoList);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attendance Save Success!").show();
                obList.clear();
                tblAttendance.setItems(obList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}