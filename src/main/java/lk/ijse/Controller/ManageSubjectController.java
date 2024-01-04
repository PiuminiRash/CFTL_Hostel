package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.SectionsDto;
import lk.ijse.dto.StaffDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.tm.AddTeacherTm;
import lk.ijse.dto.tm.SectionTm;
import lk.ijse.dto.tm.StaffTm;
import lk.ijse.dto.tm.StudentTm;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.util.List;

public class ManageSubjectController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSubjectId;

    @FXML
    private JFXTextField txtSubjectName;

    @FXML
    private JFXComboBox<String> cmbBuckets;

    @FXML
    private TableView<SectionTm> tblSection;

    @FXML
    private TableColumn<?, ?> colSection;

    @FXML
    private TableColumn<?, ?> colRadio;

    @FXML
    private TableView<StaffTm> tblTeacher;

    @FXML
    private TableColumn<?, ?> colTeacherId;

    @FXML
    private TableColumn<?, ?> colTeacherName;

    @FXML
    private TableColumn<?, ?> colSelect;

    private SectionModel sectionModel = new SectionModel();

    private StaffModel staffModel = new StaffModel();

    private SubjectModel subjectModel = new SubjectModel();

    private PlaceSubjectModel placeSubjectModel = new PlaceSubjectModel();

    private SubjectDetailsModel subjectDetailsModel = new SubjectDetailsModel();

    private ObservableList<SectionTm> obList = FXCollections.observableArrayList();

    private ObservableList<StaffTm> obListA = FXCollections.observableArrayList();

    public void initialize() {
        sectionSetCellValueFactory();
        teacherSetCellValueFactory();
        loadSection();
        loadTeacher();
        generateNextSubjectCode();

        cmbBuckets.getItems().addAll("Bucket 01", "Bucket 02" , "Bucket 03");
    }

    private void generateNextSubjectCode() {
        try {
            String orderId = subjectModel.generateNextSubjectCode();
            txtSubjectId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void sectionSetCellValueFactory() {
        colSection.setCellValueFactory(new PropertyValueFactory<>("SectionName"));
        colRadio.setCellValueFactory(new PropertyValueFactory<>("Select"));
    }

    private void teacherSetCellValueFactory() {
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        colSelect.setCellValueFactory(new PropertyValueFactory<>("select"));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    private void loadSection() {
        var model = new SectionModel();

        try {
            List<SectionsDto> sectionsDtos = sectionModel.getAllSections();
            for (SectionsDto dto : sectionsDtos) {
                RadioButton select = new RadioButton(" ");
                obList.add(
                        new SectionTm(
                                dto.getSectionName(),
                                select
                        )
                );
            }
            tblSection.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTeacher() {
        var model = new StaffModel();

        String type = "Teacher";
        try {
            List<StaffDto> staffDtos = staffModel.getAllStaffType(type);
            for (StaffDto dto : staffDtos) {
                RadioButton select = new RadioButton(" ");
                obListA.add(
                        new StaffTm(
                                dto.getStaffId(),
                                dto.getStaffName(),
                                select
                        )
                );
            }
            tblTeacher.setItems(obListA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
