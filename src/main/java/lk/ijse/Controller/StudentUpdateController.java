package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.SectionsDto;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.SubjectDto;
import lk.ijse.dto.TeacherDto;
import lk.ijse.model.SectionModel;
import lk.ijse.model.StudentModel;
import lk.ijse.model.SubjectModel;

import java.sql.SQLException;
import java.util.List;

public class StudentUpdateController {
    @FXML
    private JFXComboBox<String> cmbStudentId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbSection;

    @FXML
    private JFXComboBox<String> cmbBucket01;

    @FXML
    private JFXComboBox<String> cmbBucket02;

    @FXML
    private JFXComboBox<String> cmbBucket03;

    public void initialize() {
        loadAllStudent();
        loadSection();
    }

    private void loadAllStudent() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<StudentDto> idList = StudentModel.getAllStudent();

            for ( StudentDto dto : idList) {
                obList.add(dto.getStudentId());
            }
            cmbStudentId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadSection() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SectionsDto> sectionsDtos = SectionModel.getAllSections();

            for (SectionsDto dto : sectionsDtos) {
                obList.add(dto.getSectionName());
            }
            cmbSection.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        cmbStudentId.setValue("");
        txtStudentName.setText("");
        txtAddress.setText("");
        cmbSection.setValue("");
        cmbBucket01.setValue("");
        cmbBucket02.setValue("");
        cmbBucket03.setValue("");

    }

    public void cmbSectionAction(ActionEvent actionEvent) {
        String selectSection = cmbSection.getValue();
        if (selectSection != null) {
            loadBucket1(selectSection);
            loadBucket2(selectSection);
            loadBucket3(selectSection);
        }
    }

    public void loadBucket1 (String section) {
        String bucket = "Bucket1";

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SubjectDto> subjectDtos1 = SubjectModel.bucketSub(section,bucket);

            for (SubjectDto dto : subjectDtos1) {
                obList.add(dto.getSubjectName());
            }
            cmbBucket01.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadBucket2 (String section) {
        String bucket = "Bucket2";

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SubjectDto> subjectDtos = SubjectModel.bucketSub(section,bucket);

            for (SubjectDto dto : subjectDtos) {
                obList.add(dto.getSubjectName());
            }
            cmbBucket02.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadBucket3 (String section) {
        String bucket = "Bucket3";

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SubjectDto> subjectDtos1 = SubjectModel.bucketSub(section,bucket);

            for (SubjectDto dto : subjectDtos1) {
                obList.add(dto.getSubjectName());
            }
            cmbBucket03.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnNextOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id =  cmbStudentId.getValue();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String section = cmbSection.getValue();
        String bucket1 = cmbBucket01.getValue();
        String bucket2 = cmbBucket02.getValue();
        String bucket3 = cmbBucket03.getValue();

        try{

            var dto = new StudentDto (id,name,address,section,bucket1,bucket2,bucket3);
            boolean isUpdate = StudentModel.updateStudent(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"updated Student").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        String id = cmbStudentId.getValue();

        try{
            StudentDto dto = StudentModel.searchStudent(id);
            txtStudentName.setText(dto.getStudentName());
            txtAddress.setText(dto.getStudentAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
