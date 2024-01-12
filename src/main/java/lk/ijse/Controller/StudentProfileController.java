package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.model.*;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class StudentProfileController {
    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXComboBox<String> cmbSection;

    @FXML
    private JFXComboBox<String> cmbBucket01;

    @FXML
    private JFXComboBox<String> cmbBucket03;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbBucket02;

    @FXML
    private JFXTextField txtGardianName;

    @FXML
    private JFXTextField txtGardianMail;

    @FXML
    private JFXTextField txtGardianContactNo;

    @FXML
    private JFXTextField txtRoomNo;

    @FXML
    private ListView<String> listPayment;

    @FXML
    private JFXTextField txtRoomName;

    private StudentModel studentModel = new StudentModel();

    private GardianModel gardianModel = new GardianModel();

    private RoomModel roomModel = new RoomModel();

    private SectionModel sectionModel = new SectionModel();

    private PaymentModel paymentModel = new PaymentModel();

    private ObservableList<String> obList = FXCollections.observableArrayList();

    public void initialize(){
        loadSection();
    }

    @FXML
    void btnRoomDetailsOnAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/roommanage_form.fxml"));
            AnchorPane RoomManagePane = loader.load();

            String roomNo = txtRoomNo.getText();

            RoomManageController roomManageController = loader.getController();

            roomManageController.loadRoom(roomNo);

            root.getChildren().clear();
            root.getChildren().add(RoomManagePane);
        } catch (IOException e) {

        }
    }

    @FXML
    void cmbSectionAction(ActionEvent event) {
        String selectSection = cmbSection.getValue();
        if (selectSection != null) {
            loadBucket1(selectSection);
            loadBucket2(selectSection);
            loadBucket3(selectSection);
        }
    }

    public void loadStudent(String studentId){
        try{
            StudentDto studentDto = studentModel.searchStudent(studentId);
            txtStudentId.setText(studentId);
            txtStudentName.setText(studentDto.getStudentName());
            txtAddress.setText(studentDto.getStudentAddress());
            cmbSection.setValue(studentDto.getSection());
            cmbBucket01.setValue(studentDto.getBucket01());
            cmbBucket02.setValue(studentDto.getBucket02());
            cmbBucket03.setValue(studentDto.getBucket03());
            txtRoomNo.setText(studentDto.getRoomNo());

            String roomNo = txtRoomNo.getText();

            GardianDto gardianDto = gardianModel.searchGuardian(studentId);
            txtGardianName.setText(gardianDto.getGardianName());
            txtGardianMail.setText(gardianDto.getEmail());
            txtGardianContactNo.setText(gardianDto.getContactNo());

            RoomDto roomDto = roomModel.searchRoom(roomNo);
            txtRoomName.setText(roomDto.getRoomName());

            try {
                List<PaymentDto> dtoList = paymentModel.getAllPayment(studentId);

                for (PaymentDto dto : dtoList) {
                    obList.add(
                            dto.getMonth()
                    );
                }

                listPayment.setItems(obList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    private boolean validateStudent() {
        boolean isValidate = true;
        boolean id = Pattern.matches("[S][\\d]{2,}",txtStudentId.getText());
        if (!id){
            showErrorNotification("Invalid id", "The id you entered is invalid");
            isValidate = false;
        }
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id =  txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String section = cmbSection.getValue();
        String bucket1 = cmbBucket01.getValue();
        String bucket2 = cmbBucket02.getValue();
        String bucket3 = cmbBucket03.getValue();
        String room = txtRoomNo.getText();
        try{
            var dto = new StudentDto (id,name,address,section,bucket1,bucket2,bucket3,room);
            boolean isValidate = validateStudent();
            if (isValidate) {
                boolean isUpdate = studentModel.updateStudent(dto);
                if (isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"updated Student").show();
                }
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
