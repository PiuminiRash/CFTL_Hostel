package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.model.*;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class StudentManageController {
    @FXML
    public JFXTextField txtRoomName;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtGardianName;

    @FXML
    private AnchorPane root;

    @FXML
     private JFXTextField txtRoomNo;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtStudentId;

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

    @FXML
    private ImageView imageStudent;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnNext;

    private String section;

    private StudentModel studentModel = new StudentModel();

    private SectionModel sectionModel = new SectionModel();

    public void initialize() {
        loadSection();
        generateNextStudentId();
    }

    private void generateNextStudentId() {
        try {
            String studentId = studentModel.generateNextStudentId();
            txtStudentId.setText(studentId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String studentId = txtStudentId.getText();
        String studentName = txtStudentName.getText();
        String address = txtAddress.getText();
        String section = cmbSection.getValue();
        String bucket1 = cmbBucket01.getValue();
        String bucket2 = cmbBucket02.getValue();
        String bucket3 = cmbBucket03.getValue();
        String roomNo = txtRoomNo.getText();

        String guardianName = txtGardianName.getText();
        String Email = txtEmail.getText();
        String ContactNo = txtContactNo.getText();

//        try {
//            if (!validateStudent()){
//                return;
//            }
//            var dto = new StudentDto(studentId,studentName,address,section,bucket1,bucket2,bucket3);
//            boolean isSaved = StudentModel.saveStudent(dto);
//
//            if (isSaved) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Student Saved!").show();
//                clearFields();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
        try {
            if (!validateStudent() || !validateGuardian()) {
                return;
            }

            StudentDto studentDto = new StudentDto(studentId, studentName, address, section, bucket1, bucket2, bucket3 , roomNo);
            boolean isStudentSaved = StudentModel.saveStudent(studentDto);

            if (isStudentSaved) {
                GardianDto guardianDto = new GardianDto(studentId, guardianName, Email, ContactNo);
                boolean isGuardianSaved = GardianModel.saveGardian(guardianDto);

                if (isGuardianSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Details Saved!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error saving guardian").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateStudent() {
        boolean isValidate = true;
//        boolean id = Pattern.matches("[S][\\d]{2,}",txtStudentId.getText());
//        if (!id){
//            showErrorNotification("Invalid id", "The id you entered is invalid");
//            isValidate = false;
//        }
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtStudentName.getText());
        if (!name){
            showErrorNotification("Invalid Student Name", "The Student name you entered is invalid");
            isValidate = false;
        }
        boolean address = Pattern.matches("[A-Za-z]{5,}",txtAddress.getText());
        if(!address){
            showErrorNotification("invalid address","The address you entered is invalid");
            isValidate = false;
        }
        boolean section = Pattern.matches("[A-Za-z]{2,}",cmbSection.getValue());
        if (!section){
            showErrorNotification("Invalid section", "The section you entered is invalid");
            isValidate = false;
        }
        //boolean bucket1 = Pattern.matches("([A-Z])\\w+",cmbBucket01.getValue());
//        if (!bucket1){
//            showErrorNotification("Invalid bucket 1", "The bucket 1 you entered is invalid");
//            isValidate = false;
//        }boolean bucket2 = Pattern.matches("([A-Z])\\w+",cmbBucket02.getValue());
//        if (!bucket2){
//            showErrorNotification("Invalid bucket 2", "The bucket 2 you entered is invalid");
//            isValidate = false;
//        }boolean bucket3 = Pattern.matches("([A-Z])\\w+",cmbBucket03.getValue());
//        if (!bucket3){
//            showErrorNotification("Invalid bucket 3", "The bucket 3 you entered is invalid");
//            isValidate = false;
//        }
        return isValidate;
    }

    private boolean validateGuardian(){
        boolean isValidate = true;
        boolean name = Pattern.matches("[A-Za-z]{5,}", txtStudentName.getText());
        if (!name){
            showErrorNotification("Invalid Student Name", "The Student name you entered is invalid");
            isValidate = false;
        }
        boolean email = Pattern.matches("^(.+)@(.+)$",txtEmail.getText());
        if (!email) {
            showErrorNotification("Invalid Email","The Email you entered is invalid");
        }
        //boolean contactNo Pattern.matches()
        return isValidate;
    }

    private void showErrorNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showError();
    }

    private void loadSection() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SectionsDto> sectionsDtos = sectionModel.getAllSections();

            for (SectionsDto dto : sectionsDtos) {
                obList.add(dto.getSectionName());
            }
            cmbSection.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtStudentId.setText("");
        txtStudentName.setText("");
        txtAddress.setText("");
        cmbSection.setValue("");
        cmbBucket01.setValue("");
        cmbBucket02.setValue("");
        cmbBucket03.setValue("");
        txtGardianName.setText("");
        txtEmail.setText("");
        txtContactNo.setText("");

        generateNextStudentId();
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
}
