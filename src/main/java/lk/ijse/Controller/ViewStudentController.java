package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.dto.tm.StudentTm;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.StudentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ViewStudentController {
    @FXML
    public AnchorPane rootNode;

    @FXML
    public TableColumn<?,?> colEdit;

    @FXML
    private TableColumn<?,?> colDelete;

    @FXML
    private TableView<StudentTm> tblStudentDetails;

    @FXML
    private TableColumn<?,?> colStudentId;

    @FXML
    private TableColumn<?,?> colStudentName;

    @FXML
    private TableColumn<?,?> colAddress;

    @FXML
    private TableColumn<?,?> colSection;

    @FXML
    private TableColumn<?,?> colAction;

    @FXML
    private JFXButton Update;

    @FXML
    private JFXButton Delete;

    private StudentModel studentModel = new StudentModel();

    private ObservableList<StudentTm> obList = FXCollections.observableArrayList();


    public void initialize(){
        setCellValueFactory();
        loadAllStudent();
    }

    private void setCellValueFactory(){
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("StudentAddress"));
        colSection.setCellValueFactory(new PropertyValueFactory<>("Section"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("UpdateBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("DeleteBtn"));
    }

    private void loadAllStudent(){
        var model = new StudentModel();

        ObservableList<StudentTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentDto> dtoList = studentModel.getAllStudent();

            for (StudentDto dto : dtoList) {
                Button updateBtn = new Button("Update");
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn,dto.getStudentId());
                setUpdateButtonOnAction(updateBtn,dto.getStudentId());

                obList.add(
                        new StudentTm(
                                dto.getStudentId(),
                                dto.getStudentName(),
                                dto.getStudentAddress(),
                                dto.getSection(),
                                updateBtn,
                                deleteBtn
                        )
                );
            }

            tblStudentDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String studentId) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove student ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(studentTm -> studentTm.getStudentId().equals(studentId));
                tblStudentDetails.refresh();

                DeleteStudent(studentId);
                loadAllStudent();
            }
        });
    }

    private void DeleteStudent(String studentId){
        try {
            boolean isDeleted = StudentModel.deleteStudent(studentId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Student not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblStudentDetails.refresh();
    }

    private void setUpdateButtonOnAction(Button updateBtn, String StudentId) {
        updateBtn.setOnAction((e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studentupdate_form.fxml"));
                AnchorPane updateForm = loader.load();
                rootNode.getChildren().clear();
                rootNode.getChildren().add(updateForm);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
