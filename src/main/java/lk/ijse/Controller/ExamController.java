package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.StudentDto;

import java.sql.SQLException;
import java.time.Month;
import java.time.Year;

public class ExamController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<Year> cmbYear;

    @FXML
    private ListView<Month> listMonth;

    public void initialize() {
        loadMonth();
    }

    public void loadMonth(){
        ObservableList<Month> months = FXCollections.observableArrayList(Month.values());
        listMonth.setItems(months);
    }
}
