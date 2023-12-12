package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.AddTeacherTm;
import lk.ijse.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SubjectUpdateController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXTextField txtSubName;

    @FXML
    private JFXComboBox<String> comboBoxBucket;

    @FXML
    private JFXButton btnNext;

    @FXML
    private JFXTextField txtSubCode;

    @FXML
    private JFXComboBox<String> comboBoxTeacher;

    @FXML
    private JFXButton btnAddTeacher;

    @FXML
    private TableView<AddTeacherTm> tblAddteachers;

    @FXML
    private TableColumn<?, ?> colTeacherId;

    @FXML
    private TableColumn<?, ?> colTeacherName;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXComboBox<String> comboBoxSection;

    @FXML
    private JFXComboBox<String> cmbSubCode;

    @FXML
    private Label lblTeacherName;

    private ObservableList<AddTeacherTm> obList = FXCollections.observableArrayList();

   /* public void initialize() {
        setCellValueFactory();
        loadSection();
        loadBucket();
        loadTeacher();
    }

    private void setCellValueFactory() {
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("Btn"));
    }

    @FXML
    void btnAddTeacherOnAction(ActionEvent event) {
        String id = comboBoxTeacher.getValue();
        String name = lblTeacherName.getText();
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            for (int i = 0; i < tblAddteachers.getItems().size(); i++) {
                if (colTeacherId.getCellData(i).equals(id)) {

                    tblAddteachers.refresh();
                    return;
                }
            }
        }
        var addTeacherTm = new AddTeacherTm(id, name, btn);

        obList.add(addTeacherTm);

        tblAddteachers.setItems(obList);
    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblAddteachers.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblAddteachers.refresh();
            }
        });
    }


    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subjecttimetable_form.fxml"));
//        AnchorPane updateForm = loader.load();
        rootNode.getChildren().clear();
        rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/subjecttimetable_form.fxml")));
    }

    private void loadBucket() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<BucketDto> bucketDtos = BucketModel.getAllBucket();
            for (BucketDto dto : bucketDtos) {
                obList.add(dto.getBucketId());
            }
            comboBoxBucket.setItems(obList);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadSection() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SectionsDto> sectionsDtos = SectionModel.getAllSections();
            for (SectionsDto dto : sectionsDtos) {
                obList.add(dto.getSectionName());
            }
            comboBoxSection.setItems(obList);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTeacher() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TeacherDto> teacherDtos = TeacherModel.getAllTeacher();

            for (TeacherDto dto : teacherDtos) {
                obList.add(dto.getTeacherId());
                lblTeacherName.setText(dto.getTeacherName());
            }
            comboBoxTeacher.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboBoxBucketOnAction(ActionEvent actionEvent) {
        String id = comboBoxBucket.getValue();

        try {
            BucketDto dto = BucketModel.searchBucket(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboBoxTeacherOnAction(ActionEvent actionEvent) {
        String id = comboBoxTeacher.getValue();

        try {
            TeacherDto dto = TeacherModel.searchTeacher(id);
            lblTeacherName.setText(dto.getTeacherName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboBoxSectionOnAction(ActionEvent actionEvent) {
        String section = comboBoxSection.getValue();

        try {
            SectionsDto dto = SectionModel.searchSection(section);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String section = comboBoxSection.getValue();
        String bucket = comboBoxBucket.getValue();
        String subjectCode = txtSubCode.getText();
        String subjectName = txtSubName.getText();

        try{

            var dto = new SubjectDto(section,bucket,subjectCode,subjectName);
            boolean isUpdate = SubjectModel.updateSubject(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"updated Subject").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }*/
}
