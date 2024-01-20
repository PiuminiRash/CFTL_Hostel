package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.Custom.StudentBO;
import lk.ijse.dto.StudentDto;
import lk.ijse.dto.tm.StudentTm;
import lk.ijse.dto.StudentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ViewStudentController {
    @FXML
    public AnchorPane root;

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

    private StudentModel studentModel = new StudentModel();

    StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);

    private ObservableList<StudentTm> obList = FXCollections.observableArrayList();


    public void initialize(){
        setCellValueFactory();
        loadAllStudent();

        tblStudentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                openStudentDetails(newValue.getStudentId());
            }
        });
    }

    private void setCellValueFactory(){
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("StudentAddress"));
        colSection.setCellValueFactory(new PropertyValueFactory<>("Section"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("DeleteBtn"));
    }

    private void loadAllStudent(){
        var model = new StudentModel();

        try {
            List<StudentDto> dtoList = studentModel.getAllStudent();

            for (StudentDto dto : dtoList) {
                Button deleteBtn = new Button("Delete");

                setDeleteButtonOnAction(deleteBtn,dto.getStudentId());

                obList.add(
                        new StudentTm(
                                dto.getStudentId(),
                                dto.getStudentName(),
                                dto.getStudentAddress(),
                                dto.getSection(),
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
            boolean isDeleted = studentBO.deleteStudent(studentId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Student not deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblStudentDetails.refresh();
    }

    private void openStudentDetails(String studentId) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studentprofile_form.fxml"));
                AnchorPane studentProfilePane = loader.load();

                StudentProfileController studentProfileController = loader.getController();

                studentProfileController.loadStudent(studentId);

                root.getChildren().clear();
                root.getChildren().add(studentProfilePane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public void txtSearchOnAction(MouseEvent mouseEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/managestudent_form.fxml")));
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentpayments_form.fxml")));
    }
}
