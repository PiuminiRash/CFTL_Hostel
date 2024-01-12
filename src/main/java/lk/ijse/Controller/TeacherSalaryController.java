package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.AttendanceDto;
import lk.ijse.dto.StaffDto;
import lk.ijse.dto.tm.TeacherSalaryTm;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TeacherSalaryController {
    @FXML
    private JFXComboBox<Month> cmbMonth;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableColumn<?,?> colName;

    @FXML
    private TableColumn<?,?> colId;

    @FXML
    private TableView<TeacherSalaryTm> tblTeacherSalary;

    @FXML
    private TableColumn<?,?> colAttendanceCount;

    @FXML
    private TableColumn<?,?> colDayPay;

    @FXML
    private TableColumn<?,?> colSalary;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Label lblTeacherName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXComboBox<String> cmbTeacherId;

    @FXML
    private JFXButton btnAdd;

    private String month;

    private StaffModel staffModel = new StaffModel();

    private AttendanceModel attendanceModel = new AttendanceModel();

    private ObservableList<TeacherSalaryTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setMonth();
        month = String.valueOf(cmbMonth.getValue());
    }

    public void setMonth() {
        ObservableList<Month> months = FXCollections.observableArrayList(Month.values());
        cmbMonth.setItems(months);

        cmbMonth.setPromptText("Select a month");
    }

    public void cmbMonthOnAction(ActionEvent actionEvent) throws SQLException {
        CalSalary();
    }

    public void CalSalary() throws SQLException {
        List<StaffDto> teacherDtoList = staffModel.getAllStaff();
        for (int i=0; i<teacherDtoList.size(); i++) {
            List<AttendanceDto> attendanceDtoList = AttendanceModel.getAttendance(month);
            for (int j=0; j<attendanceDtoList.size();j++) {
                List<TeacherSalaryTm> dtoList = new ArrayList<>();
                for (TeacherSalaryTm dto : dtoList) {
                    obList.add(
                            new TeacherSalaryTm(
                                    dto.getTeacherId(),
                                    dto.getTeacherName(),
                                    dto.getDatPay(),
                                    dto.getAttendanceCount(),
                                    dto.getSalary()
                            )
                    );
                }
            }
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
        colDayPay.setCellValueFactory(new PropertyValueFactory<>("datPay"));
        colAttendanceCount.setCellValueFactory(new PropertyValueFactory<>("attendanceCount"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }
}
