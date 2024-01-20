package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.AttendanceDto;
import lk.ijse.dto.tm.AttendanceViewTm;
import lk.ijse.dto.AttendanceModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TeacherAttendanceController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton btnUpdateAttendance;

    @FXML
    private TableView<AttendanceViewTm> tblviewAttendance;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTeacherId;

    @FXML
    private TableColumn<?, ?> colTeacherName;

    @FXML
    private DatePicker Date;

    @FXML
    private String date;

    private ObservableList<AttendanceViewTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
    }

    public void dateDateOnAction(ActionEvent actionEvent) {
        try {
            String selectedDate = String.valueOf(Date.getValue());
            if (selectedDate != null) {
                List<AttendanceDto> attendanceList = AttendanceModel.getAttendance(selectedDate);

                for (AttendanceDto dto : attendanceList) {
                    String id = dto.getTeacherId();
                    obList.add(
                            new AttendanceViewTm(
                                    dto.getDate(),
                                    dto.getTeacherId(),
                                    id
                            )
                    );
                }

                tblviewAttendance.setItems(obList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        rootNode.getChildren().clear();
        rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/updateattendance_form.fxml")));
    }
}
