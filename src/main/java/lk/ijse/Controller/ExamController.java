package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ExamController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> cmbYear;

    @FXML
    private JFXComboBox<String> cmbMonth;

    @FXML
    private JFXComboBox<String> cmbSubject;

    @FXML
    private TableView<?> tblExam;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colMark;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtSubjectMark;

    @FXML
    void btnAbsuntOnAction(ActionEvent event) {

    }

    @FXML
    void btnMarkOnAction(ActionEvent event) {

    }

    public void cmbSubjectOnnAction(ActionEvent actionEvent) {

    }
}
