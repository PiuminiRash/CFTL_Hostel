package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.tm.SubjectStudentTm;
import lk.ijse.model.StudentModel;

import java.sql.SQLException;
import java.util.List;

public class SubjectStudentController {
    @FXML
    private TableView<SubjectStudentTm> tblSubjectStudent;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colSection;

    @FXML
    private TableColumn<?, ?> colBucket1;

    @FXML
    private TableColumn<?, ?> colBucket2;

    @FXML
    private TableColumn<?, ?> colBucket3;

    private StudentModel studentModel = new StudentModel();

    public void initialize(){
        setCellValueFactory();
        loadAllStudent();
    }

    private void setCellValueFactory(){
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colSection.setCellValueFactory(new PropertyValueFactory<>("Section"));
        colBucket1.setCellValueFactory(new PropertyValueFactory<>("Bucket1"));
        colBucket2.setCellValueFactory(new PropertyValueFactory<>("Bucket2"));
        colBucket3.setCellValueFactory(new PropertyValueFactory<>("Bucket3"));
    }

    private void loadAllStudent(){
        var model = new StudentModel();

        ObservableList<SubjectStudentTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentDto> dtoList = studentModel.getAllStudent();

            for (StudentDto dto : dtoList) {
                obList.add(
                        new SubjectStudentTm(
                                dto.getStudentId(),
                                dto.getStudentName(),
                                dto.getSection(),
                                dto.getBucket01(),
                                dto.getBucket02(),
                                dto.getBucket03()
                        )
                );
            }

            tblSubjectStudent.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
