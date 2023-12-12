package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.TeacherDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.dto.tm.StudentTm;
import lk.ijse.dto.tm.TeacherTm;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.StudentModel;
import lk.ijse.model.TeacherModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewTeacherController {
    public AnchorPane rootNode;
    @FXML
    private TableColumn<?,?> colEdit;

    @FXML
    private TableColumn<?,?> colDelete;

    @FXML
    private TableColumn<? ,?> colTeacherId;

    @FXML
    private TableColumn<? ,?> colTeacherName;

    @FXML
    private TableColumn<? ,?> colAddress;

    @FXML
    private TableColumn<? ,?> colNIC;

    @FXML
    private TableView<TeacherTm> tblTaecherDetails;

    private TeacherModel teacherModel = new TeacherModel();

    private ObservableList<TeacherTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        loadAllTeacherDetails();
    }

    private void setCellValueFactory() {
        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("Remove"));
    }

    private void loadAllTeacherDetails() {
        var model = new TeacherModel();

        ObservableList<TeacherTm> obList = FXCollections.observableArrayList();

        try {
            List<TeacherDto> dtoList = teacherModel.getAllTeacher();

            for (TeacherDto dto : dtoList) {
                Button updateBtn = new Button("Update");
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn,dto.getTeacherId());
                setUpdateButtonOnAction(updateBtn,dto.getTeacherId());

                obList.add(
                        new TeacherTm(
                                dto.getTeacherId(),
                                dto.getTeacherName(),
                                dto.getAddress(),
                                dto.getNIC(),
                                updateBtn,
                                deleteBtn
                        )
                );
            }

            tblTaecherDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpdateButtonOnAction(Button updateBtn, String teacherId) {
        updateBtn.setOnAction((e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/teahersdetailsupdate_form.fxml"));
                AnchorPane updateForm = loader.load();
                rootNode.getChildren().clear();
                rootNode.getChildren().add(updateForm);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setDeleteButtonOnAction(Button deleteBtn , String teacherId) {
        deleteBtn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove Teacher ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                obList.removeIf(teacherTm -> teacherTm.getTeacherId().equals(teacherId));
                tblTaecherDetails.refresh();

                DeleteTeacher(teacherId);
                loadAllTeacherDetails();
            }
        });
    }

    private void DeleteTeacher(String teacherId){
        try {
            boolean isDeleted = TeacherModel.deleteTeacher(teacherId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblTaecherDetails.refresh();
    }

}
